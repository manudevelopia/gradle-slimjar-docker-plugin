package info.developia.gradle.docker.slimjar

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleDockerPlugin implements Plugin<Project> {

    void apply(Project project) {
        def dockerExtension = createExtension(project)

//        project.tasks.create('docker', DockerTask).dockerExtension = dockerExtension
//        project.tasks.create('slimjar', DockerTask){
//            group = "docker"
//        }.ext = ext


        project.tasks.register("slimjar", DockerTask) {
            group = "docker"
            image = dockerExtension.image
            version = dockerExtension.version
            doLast {
                println "Hello from plugin 'info.developia.gradle.docker.slimjar'"
            }
        }
    }

    static DockerExtension createExtension(Project project) {
        def extension = project.extensions.create('docker', DockerExtension)
        extension.with {
            image = project.rootProject.name
            version = project.rootProject.version
        }
        return extension
    }
}
