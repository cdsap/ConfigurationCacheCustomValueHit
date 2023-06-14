package io.github.cdsap.configurationcachehit

import org.gradle.api.provider.ValueSource
import org.gradle.api.provider.ValueSourceParameters
import org.gradle.process.ExecOperations
import javax.inject.Inject

abstract class ConfigurationCacheHitAbstract : ValueSource<String, ValueSourceParameters.None> {
    val fileName = "build/.configuration_cache_hit"

    @get:Inject
    abstract val execOperations: ExecOperations
}
