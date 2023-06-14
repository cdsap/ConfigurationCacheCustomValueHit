package io.github.cdsap.configurationcachehit

import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.process.ExecOperations
import javax.inject.Inject

abstract class ConfigurationCacheWriteBuildService :
    BuildService<BuildServiceParameters.None>, AutoCloseable {

    @get:Inject
    abstract val execOperations: ExecOperations

    override fun close() {
        execOperations.exec {
            commandLine("sh", "-c", "export  1 >> build/.configuration_cache_hit ")
        }
    }
}
