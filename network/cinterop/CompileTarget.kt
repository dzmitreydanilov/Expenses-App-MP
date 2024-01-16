package tasks.cinterop

import org.jetbrains.kotlin.konan.target.KonanTarget

@Suppress("EnumEntryName")
enum class CompileTarget {
    iosX64,
    iosArm64,
    iosSimulatorArm64;
    /**
     * @see [KonanTarget](https://github.com/JetBrains/kotlin/blob/v1.8.10/native/utils/src/org/jetbrains/kotlin/konan/target/KonanTarget.kt)
     */
    companion object {
        fun byKonanName(konanName: String): CompileTarget? {
            return when (konanName) {
                KonanTarget.IOS_X64.name -> iosX64
                KonanTarget.IOS_ARM64.name -> iosArm64
                KonanTarget.IOS_SIMULATOR_ARM64.name -> iosSimulatorArm64
                else -> null
            }
        }
    }
}