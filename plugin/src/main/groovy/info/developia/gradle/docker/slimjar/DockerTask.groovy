package info.developia.gradle.docker.slimjar

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.WorkResult

class DockerTask extends DefaultTask {
    @Input
    String image
    @Input
    String version

    @TaskAction
    def run() {
        def destinationFolder = "docker-slimjar"
        copyApplicationDependencies(destinationFolder)
        copyApplicationJar(destinationFolder)
        createImage()
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

    private void createImage() {
        project.exec {
            workingDir project.rootDir
            executable 'docker'
            args 'build', '-f', 'docker/Dockerfile', '.', '-t', "$image:$version"
        }
    }
}
