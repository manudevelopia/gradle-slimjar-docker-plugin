package info.developia.gradle.docker.slimjar

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.WorkResult

class DockerTask extends DefaultTask {

    @TaskAction
    def run() {
        copyApplicationDependencies()
        copyApplicationJar()
    }

    private void copyApplicationDependencies() {
        project.configurations.runtimeClasspath.each { dependency ->
            project.copy {
                from project.layout.buildDirectory.file(dependency.path)
                into project.layout.buildDirectory.dir("${project.buildDir}/docker/slimjar")
            }
        }
//        project.copy {
//            from project.configurations.runtimeClasspath
//            into "${project.buildDir}/docker-slimjar"
//        }
    }

    private WorkResult copyApplicationJar() {
        project.copy {
            from "${project.buildDir}/libs/${project.name}.jar"
            into project.layout.buildDirectory.dir("${project.buildDir}/docker-slimjar")
        }
    }
}
