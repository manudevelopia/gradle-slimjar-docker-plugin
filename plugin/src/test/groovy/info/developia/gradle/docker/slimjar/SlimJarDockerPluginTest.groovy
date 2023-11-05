package info.developia.gradle.docker.slimjar

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class SlimJarDockerPluginTest extends Specification {

    def "plugin registers task"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.plugins.apply("info.developia.gradle.docker.slimjar")

        then:
        project.tasks.named("slimjar") != null
    }
}
