package info.developia.gradle.docker

import spock.lang.Specification
import spock.lang.TempDir
import org.gradle.testkit.runner.GradleRunner

class GradleDockerPluginFunctionalTest extends Specification {
    @TempDir
    private File projectDir

    private getBuildFile() {
        new File(projectDir, "build.gradle")
    }

    private getSettingsFile() {
        new File(projectDir, "settings.gradle")
    }

    def "can run task"() {
        given:
        settingsFile << ""
        buildFile << """
        plugins {
            id('info.developia.gradle.docker.greeting')
        }
        """

        when:
        def runner = GradleRunner.create()
        runner.forwardOutput()
        runner.withPluginClasspath()
        runner.withArguments("greeting")
        runner.withProjectDir(projectDir)
        def result = runner.build()

        then:
        result.output.contains("Hello from plugin 'info.developia.gradle.docker.greeting'")
    }
}
