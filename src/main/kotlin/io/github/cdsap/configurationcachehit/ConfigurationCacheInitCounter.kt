package io.github.cdsap.configurationcachehit

abstract class ConfigurationCacheInitCounter : ConfigurationCacheHitAbstract() {

    override fun obtain(): String {
        execOperations.exec {
            try {
                commandLine("sh", "-c", "echo 1 >> $fileName")
            } catch (e: Exception) {
                println(e.message)
            }
        }
        return ""
    }
}


