package info.developia.gradle.docker.slimjar

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

import static SlimJarDockerPlugin.LIBS_FOLDER

class DockerTask extends DefaultTask {
    @Input
    String image
    @Input
    String version
    @Input
    String dockerfile
    @Input
    String destinationFolder

    @TaskAction
    def run() {
            try {
            copyApplicationDependencies()
            copyApplicationJar()
            createImage()
        } finally {
            cleanUpDependencies()
        }
    }

    private void copyApplicationDependencies() {
        println "Copying Application Dependencies Jars to $destinationFolder"
        project.copy {
            from project.configurations.runtimeClasspath
            into project.layout.buildDirectory.dir(destinationFolder)
            duplicatesStrategy 'exclude'
        }
    }

    private void copyApplicationJar() {
        if (destinationFolder != LIBS_FOLDER) {
            println "Copying Application Jar to $destinationFolder"
            project.copy {
                from project.layout.buildDirectory.file("$LIBS_FOLDER/${project.name}.jar")
                into project.layout.buildDirectory.dir(destinationFolder)
            }
        }
    }

    private void createImage() {
        println "Building Docker image $image:$version from $dockerfile"
        project.exec {
            workingDir project.rootDir
            executable 'docker'
            args 'build', '-f', dockerfile, '.', '-t', "$image:$version"
        }
    }

    private void cleanUpDependencies() {
        project.configurations.runtimeClasspath.each { dependency ->
            project.delete(project.layout.buildDirectory.file("$destinationFolder/$dependency.name"))
        }
    }
}
