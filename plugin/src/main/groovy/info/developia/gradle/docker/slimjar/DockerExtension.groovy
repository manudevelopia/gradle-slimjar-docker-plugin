package info.developia.gradle.docker.slimjar

class DockerExtension {
    String image
    String version

    String getImage() {
        return image
    }

    void setImage(String image) {
        this.image = image
        println "hola $image"
    }

    String getVersion() {
        return version
    }

    void setVersion(String version) {
        this.version = version
        println "hola $version"
    }
}
