package tasks.cinterop

import EXTENSION_NAME
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.IllegalStateException
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

open class CompileSwiftTask @Inject constructor(
    @Input val cinteropName: String,
    @Input val compileTarget: CompileTarget,
    @InputDirectory val pathProperty: Property<File>,
    @Input val packageNameProperty: Property<String>,
    @Optional @Input val minIosProperty: Property<Int>,
) : DefaultTask() {

    @get:Internal
    internal val targetDir: File
        get() {
            return project.buildDir.resolve("${EXTENSION_NAME}/$cinteropName/$compileTarget")
        }

    @get:OutputDirectory
    val swiftBuildDir
        get() = File(targetDir, "swiftBuild")

    @get:OutputFile
    val defFile
        get() = File(targetDir, "$cinteropName.def")

    @TaskAction
    fun produce() {
        val packageName: String = packageNameProperty.get()

        prepareBuildDirectory()
        createPackageSwift()
        val (libPath, headerPath) = buildSwift()

        createDefFile(
            libPath = libPath,
            headerPath = headerPath,
            packageName = packageName,
        )
    }

    private val minIos get() = minIosProperty.getOrElse(13)

    private val xcodeVersion: Int by lazy {
        readXcodeMajorVersion()
    }

    /**
     * Creates build directory or cleans up if it already exists
     * and copies Swift source files to it
     */
    private fun prepareBuildDirectory() {
        val path: File = pathProperty.get()

        if (swiftBuildDir.exists()) swiftBuildDir.deleteRecursively()

        swiftBuildDir.mkdirs()
        path.copyRecursively(File(swiftBuildDir, cinteropName), true)
    }

    /**
     * Creates `Package.Swift` file for the library
     */
    private fun createPackageSwift() {
        val content = """
            // swift-tools-version:5.5
            import PackageDescription

            let package = Package(
            	name: "$cinteropName",
            	products: [
            		.library(
            			name: "$cinteropName",
            			type: .static,
            			targets: ["$cinteropName"])
            	],
            	dependencies: [],
            	targets: [
            		.target(
            			name: "$cinteropName",
            			dependencies: [],
            			path: "$cinteropName")
            	]
            )
        """.trimIndent()

        File(swiftBuildDir, "Package.swift").create(content)
    }

    private fun buildSwift(): SwiftBuildResult {
        project.exec {
            executable = "xcrun"
            workingDir = swiftBuildDir
            val extraArgs = if (xcodeVersion >= 15 && compileTarget in SDKLESS_TARGETS) {
                additionalSysrootArgs()
            } else {
                emptyList()
            }
            args = generateBuildArgs() + extraArgs
            println("Command Line: ${commandLine.joinToString(" ")}")
        }

        return SwiftBuildResult(
            libPath = File(
                swiftBuildDir,
                ".build/${compileTarget.arch()}-apple-macosx/release/lib${cinteropName}.a"
            ),
            headerPath = File(
                swiftBuildDir,
                ".build/${compileTarget.arch()}-apple-macosx/release/$cinteropName.build/$cinteropName-Swift.h"
            )
        )
    }

    private fun generateBuildArgs(): List<String> = listOf(
        "swift",
        "build",
        "--arch",
        compileTarget.arch(),
        "-c",
        "release",
        "-Xswiftc",
        "-sdk",
        "-Xswiftc",
        readSdkPath(),
        "-Xswiftc",
        "-target",
        "-Xswiftc",
        "${compileTarget.archPrefix()}-apple-${operatingSystem(compileTarget)}.0${compileTarget.simulatorSuffix()}",
    )

    /** Workaround for bug in toolchain where the sdk path (via `swiftc -sdk` flag) is not propagated to clang. */
    private fun additionalSysrootArgs(): List<String> = listOf(
        "-Xcc",
        "-isysroot",
        "-Xcc",
        readSdkPath(),
    )

    private fun readSdkPath(): String {
        val stdout = ByteArrayOutputStream()

        project.exec {
            executable = "xcrun"
            args = listOf(
                "--sdk",
                compileTarget.os(),
                "--show-sdk-path",
            )
            standardOutput = stdout
        }

        return stdout.toString().trim()
    }

    private fun readXcodeMajorVersion(): Int {
        val stdout = ByteArrayOutputStream()

        project.exec {
            executable = "xcodebuild"
            args = listOf("-version")
            standardOutput = stdout
        }

        val output = stdout.toString().trim()
        val (_, majorVersion) = "Xcode (\\d+)\\..*".toRegex().find(output)?.groupValues
            ?: throw IllegalStateException("Can't find Xcode")

        return majorVersion.toInt()
    }

    private fun readXcodePath(): String {
        val stdout = ByteArrayOutputStream()

        project.exec {
            executable = "xcode-select"
            args = listOf("--print-path")
            standardOutput = stdout
        }

        return stdout.toString().trim()
    }

    /**
     * Generates Def-file for Kotlin/Native Cinterop
     *
     * Note: adds lib-file md5 hash to library in order to automatically
     * invalidate connected cinterop task
     */
    private fun createDefFile(libPath: File, headerPath: File, packageName: String) {
        val xcodePath = readXcodePath()

        val linkerPlatformVersion =
            if (xcodeVersion >= 15) compileTarget.linkerPlatformVersionName()
            else compileTarget.linkerMinOsVersionName()

        val content = """
            package = $packageName
            language = Objective-C
            headers = ${headerPath.absolutePath}

            # md5 ${libPath.md5()}
            staticLibraries = ${libPath.name}
            libraryPaths = ${libPath.parentFile.absolutePath}

            linkerOpts = -L/usr/lib/swift -$linkerPlatformVersion ${minOs(compileTarget)}.0 ${minOs(compileTarget)}.0 -L${xcodePath}/Toolchains/XcodeDefault.xctoolchain/usr/lib/swift/${compileTarget.os()}
        """.trimIndent()
        defFile.create(content)
    }

    private fun operatingSystem(compileTarget: CompileTarget): String =
        when (compileTarget) {
            CompileTarget.iosX64, CompileTarget.iosArm64, CompileTarget.iosSimulatorArm64 -> "ios$minIos"
        }

    private fun minOs(compileTarget: CompileTarget): Int =
        when (compileTarget) {
            CompileTarget.iosX64, CompileTarget.iosArm64, CompileTarget.iosSimulatorArm64 -> minIos
        }
}

private data class SwiftBuildResult(
    val libPath: File,
    val headerPath: File,
)

val SDKLESS_TARGETS = listOf(
    CompileTarget.iosArm64
)

private fun File.create(content: String) {
    bufferedWriter().use {
        it.write(content)
    }
}

private fun File.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(readBytes()))
    .toString(16)
    .padStart(32, '0')
