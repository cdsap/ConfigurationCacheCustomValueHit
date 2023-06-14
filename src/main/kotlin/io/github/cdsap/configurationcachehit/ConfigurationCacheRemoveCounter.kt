package io.github.cdsap.configurationcachehit

abstract class ConfigurationCacheRemoveCounter : ConfigurationCacheHitAbstract() {

    override fun obtain(): String {
        execOperations.exec {
            commandLine("sh", "-c", "rm -f $fileName")
        }
        return ""
    }
}

