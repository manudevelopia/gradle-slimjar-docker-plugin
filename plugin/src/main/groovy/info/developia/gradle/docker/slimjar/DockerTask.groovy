package info.developia.gradle.docker.slimjar

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.WorkResult

class DockerTask extends DefaultTask {
    def destinationFolder = "docker-slimjar"

    @TaskAction
    def run() {
        copyApplicationDependencies()
        copyApplicationJar()
    }

    private void copyApplicationDependencies() {
        project.copy {
            from project.configurations.runtimeClasspath
            into project.layout.buildDirectory.dir(destinationFolder)
        }
    }

    private WorkResult copyApplicationJar() {
        project.copy {
            from project.layout.buildDirectory.file("libs/${project.name}.jar")
            into project.layout.buildDirectory.dir(destinationFolder)
        }
    }
}
