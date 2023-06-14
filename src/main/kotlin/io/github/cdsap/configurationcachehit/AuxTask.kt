package io.github.cdsap.configurationcachehit

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction


@CacheableTask
abstract class AuxTask : DefaultTask() {

    @get:OutputDirectory
    @get:Optional
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun action() {
    }
}
