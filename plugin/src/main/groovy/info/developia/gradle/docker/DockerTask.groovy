package info.developia.gradle.docker

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DockerTask extends DefaultTask {

    @TaskAction
    def run() {
        project.configurations.runtimeClasspath.each { dependency ->
            println "Dependencies ${dependency.name} ${dependency.path}"
            project.copy {
                from project.layout.buildDirectory.file(dependency.path)
                into project.layout.buildDirectory.dir("${project.buildDir}/libs")
            }
        }
    }
}
