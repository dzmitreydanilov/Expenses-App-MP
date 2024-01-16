import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.tasks.CInteropProcess
import tasks.cinterop.CompileSwiftTask
import tasks.cinterop.CompileTarget
import tasks.cinterop.SwiftKlibEntry

const val EXTENSION_NAME = "MyKlib"

class SwiftKlibPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val objects: ObjectFactory = project.objects

        val swiftKlibEntries: NamedDomainObjectContainer<SwiftKlibEntry> =
            objects.domainObjectContainer(SwiftKlibEntry::class.java) { name ->
                objects.newInstance(SwiftKlibEntry::class.java, name)
            }

        project.extensions.add(EXTENSION_NAME, swiftKlibEntries)

        swiftKlibEntries.configureEach {
            val name: String = name

            val targetToTaskName = CompileTarget.values().associateWith {
                getTaskName(name, it)
            }

            targetToTaskName.entries.forEach { (target, taskName) ->
                tasks.register(
                    taskName,
                    CompileSwiftTask::class.java,
                    name,
                    target,
                    pathProperty,
                    packageNameProperty,
                    minIosProperty,
                )
            }
        }

        tasks.withType(CInteropProcess::class.java).configureEach {
            val cinteropTarget = CompileTarget.byKonanName(konanTarget.name)
                ?: return@configureEach

            val taskName = getTaskName(interopName, cinteropTarget)

            val task = tasks.withType(CompileSwiftTask::class.java).findByName(taskName)
                ?: return@configureEach

            settings.defFile = task.defFile
            dependsOn(task)
        }
    }
}

private fun getTaskName(cinteropName: String, cinteropTarget: CompileTarget) =
    "${EXTENSION_NAME}${cinteropName.capitalized()}${cinteropTarget.name.capitalized()}"