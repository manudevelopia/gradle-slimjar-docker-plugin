package info.developia.gradle.docker

import org.gradle.api.Project
import org.gradle.api.Plugin

class GradleDockerPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.tasks.register("slimjar", DockerTask) {
            group = "docker"
            doLast {
                project.configurations.compileClasspath.each { dependency ->
                    println "Dependencies ${dependency.name} ${dependency.path}"
                }
                project.configurations.runtimeClasspath.each {dependency ->
                }
            }
        }
    }
}
