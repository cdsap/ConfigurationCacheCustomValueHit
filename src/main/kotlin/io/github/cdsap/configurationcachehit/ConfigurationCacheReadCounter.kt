package io.github.cdsap.configurationcachehit

import org.gradle.process.internal.ExecException
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset

abstract class ConfigurationCacheReadCounter : ConfigurationCacheHitAbstract() {


    override fun obtain(): String {
        val output = ByteArrayOutputStream()
        val error = ByteArrayOutputStream()
        return try {
            execOperations.exec {
                try {
                    commandLine("sh", "-c", "cat $fileName | wc -l ")
                    standardOutput = output
                    errorOutput = error
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            String(output.toByteArray(), Charset.defaultCharset())
        } catch (e: ExecException) {
            println(e.message)
            ""
        }
    }
}
