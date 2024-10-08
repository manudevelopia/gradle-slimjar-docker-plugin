# Gradle Slimjar Docker Plugin

The `gradle-slimjar-docker-plugin` is a Gradle plugin designed to create Docker images for Java applications without generating 
a fat JAR. It ensures that Docker images have distinct layers for real code changes, separating them from unchanged dependencies 
bundled in a fat JAR.

## Why Use This Plugin?

When dependencies are bundled in a fat JAR, the resulting application artifact becomes larger, and every code change leads to a 
new artifact stored in a new Docker layer. However, not all changes are substantial; unchanged dependencies shouldn't be treated 
as real changes and should reside in a different Docker layer from your source code.

For a more in-depth explanation of this issue, refer to the following resources:
- [No Fat JAR in Docker Image](https://phauer.com/2019/no-fat-jar-in-docker-image/)
- [Don't Build Fat Jars for Docker Applications](https://medium.com/holisticon-consultants/dont-build-fat-jars-for-docker-applications-6252a5571248)

## How to Use the Plugin

1. **Add the Plugin to your `build.gradle` File:**

   Add the following snippet to the plugins section of your `build.gradle` file. Check the [Gradle Plugin page](https://plugins.gradle.org/plugin/info.developia.gradle.docker.slimjar) for the latest version.

   ```groovy
   plugins {
       id 'info.developia.gradle.docker.slimjar' version '1.0.2'
   }

By default, the plugin uses the rootProject.name and 'latest' version to build the image name. You can override this by adding a docker block to your build.gradle file.
```
docker {
    repository 'my-repository'
    image 'my-application'
    version '1.0.0'
    dockerfile 'Dockerfile'
}
```