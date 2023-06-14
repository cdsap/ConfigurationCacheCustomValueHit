package io.github.cdsap.configurationcachehit

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.gradle.scan.plugin.BuildScanExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

class ConfigurationCacheCustomValueHit : Plugin<Project> {
    override fun apply(target: Project) {
        initAuxConfigurationCacheCounterTask(target)
        initConfigurationCacheCounter(target)
        initConfigurationCacheService(target)

        val counterConfigurationCacheActions =
            target.providers.of(ConfigurationCacheReadCounter::class.java) {}

        target.gradle.rootProject {
            val buildScanExtension = extensions.findByType(BuildScanExtension::class.java)
            if (buildScanExtension != null) {
                buildScanReporting(buildScanExtension, counterConfigurationCacheActions)
            }
        }
    }

    private fun initConfigurationCacheService(target: Project) {
        val service = target.gradle.sharedServices.registerIfAbsent(
            "configurationCacheWritterService", ConfigurationCacheWriteBuildService::class.java
        ) {}

        target.tasks.withType<AuxTask>().configureEach { service.get() }
    }

    private fun initConfigurationCacheCounter(target: Project) {
        target.providers.of(ConfigurationCacheRemoveCounter::class.java) { }.get()
        target.providers.of(ConfigurationCacheInitCounter::class.java) { }.get()
    }

    private fun initAuxConfigurationCacheCounterTask(target: Project) {
        val nameTask = "forcingConfigurationCacheRegister"
        val taskProvider = target.tasks.register<AuxTask>(nameTask) {}
        target.tasks.matching { it.name != nameTask }.all { dependsOn(taskProvider) }
    }

    private fun buildScanReporting(
        buildScanExtension: BuildScanExtension,
        counterConfigurationCacheActions: Provider<String>
    ) {

        buildScanExtension.buildFinished {
            val counter = counterConfigurationCacheActions.get().trim().toInt()
            buildScanExtension.value(
                "configuration-cache-hit",
                "${counter == 1}"
            )
        }
    }
}
