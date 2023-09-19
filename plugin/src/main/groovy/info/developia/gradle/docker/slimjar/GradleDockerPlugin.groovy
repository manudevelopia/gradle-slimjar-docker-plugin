package info.developia.gradle.docker.slimjar

import org.gradle.api.Project
import org.gradle.api.Plugin

class GradleDockerPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.tasks.register("slimjar", DockerTask) {
            group = "docker"
            doLast {
                println "Hello from plugin 'info.developia.gradle.docker.slimjar'"
            }
        }
    }
}
