package info.developia.gradle.docker.slimjar

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.WorkResult

class DockerTask extends DefaultTask {

    @TaskAction
    def run() {
        def destinationFolder = "docker-slimjar"
        copyApplicationDependencies(destinationFolder)
        copyApplicationJar(destinationFolder)
    }

    private void copyApplicationDependencies(String destinationFolder) {
        project.copy {
            from project.configurations.runtimeClasspath
            into project.layout.buildDirectory.dir(destinationFolder)
        }
    }

    private WorkResult copyApplicationJar(String destinationFolder) {
        project.copy {
            from project.layout.buildDirectory.file("libs/${project.name}.jar")
            into project.layout.buildDirectory.dir(destinationFolder)
        }
    }
}
