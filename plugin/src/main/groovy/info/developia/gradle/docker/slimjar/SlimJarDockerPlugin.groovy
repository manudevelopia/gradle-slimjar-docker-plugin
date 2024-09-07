package info.developia.gradle.docker.slimjar

import org.gradle.api.Plugin
import org.gradle.api.Project

class SlimJarDockerPlugin implements Plugin<Project> {
    public static final String LIBS_FOLDER = "libs"

    void apply(Project project) {
        def dockerExtension = createExtension(project)
        project.tasks.register("slimjar", DockerTask) {
            group = "docker"
            repository = dockerExtension.repository
            image = dockerExtension.image
            version = dockerExtension.version
            dockerfile = dockerExtension.dockerfile
            destinationFolder = dockerExtension.destinationFolder
            doLast {
                println "Slim Jar Docker image $repository/$image:$version has been created"
            }
        }
        project.tasks.register("publish", PublishTask) {
            group = "docker"
            repository = dockerExtension.repository
            image = dockerExtension.image
            version = dockerExtension.version
            doLast {
                println "Docker image $repository/$image:$version has been published"
            }
        }
    }

    static DockerExtension createExtension(Project project) {
        def extension = project.extensions.create('docker', DockerExtension)
        extension.with {
            repository = 'library'
            image = project.rootProject.name
            version = 'latest'
            dockerfile = 'docker/Dockerfile'
            destinationFolder = LIBS_FOLDER
        }
        return extension
    }
}
