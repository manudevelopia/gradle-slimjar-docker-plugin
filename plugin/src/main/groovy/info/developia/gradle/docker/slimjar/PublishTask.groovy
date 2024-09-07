package info.developia.gradle.docker.slimjar

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class PublishTask extends DefaultTask {
    @Input
    String repository
    @Input
    String image
    @Input
    String version

    @TaskAction
    def run() {
        push()
    }

    private void push() {
        println "Publishing Docker image $repository$image:$version"
        project.exec {
            workingDir project.rootDir
            executable 'docker'
            args 'push', "$repository/$image:$version"
        }
    }
}
