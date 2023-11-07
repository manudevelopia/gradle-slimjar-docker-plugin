package info.developia.gradle.docker.slimjar

import org.gradle.testkit.runner.GradleRunner
import spock.lang.Specification
import spock.lang.TempDir

class GradleDockerPluginFunctionalTest extends Specification {
    @TempDir
    private File projectDir

    private getBuildFile() {
        new File(projectDir, "build.gradle")
    }

    private getSettingsFile() {
        new File(projectDir, "settings.gradle")
    }

    private getDockerFile() {
        new File(projectDir, "Dockerfile")
    }

    def "can run task"() {
        given:
        String image = "my-project"
        String version = "0.0.1"
        settingsFile << ""
        buildFile << """
            plugins {
                id 'info.developia.gradle.docker.slimjar'
            }
            configurations {
                runtimeClasspath
            }
            docker {
                image = '$image'
                version = '$version'
                dockerfile = 'Dockerfile'
            }
        """
        dockerFile << """
            FROM hello-world:linux
        """

        when:
        def runner = GradleRunner.create()
        runner.forwardOutput()
        runner.withPluginClasspath()
        runner.withArguments("slimjar")
        runner.withProjectDir(projectDir)
        def result = runner.build()

        then:
        result.output.containsIgnoreCase("Slim Jar Docker image $image:$version has been created")
    }
}
