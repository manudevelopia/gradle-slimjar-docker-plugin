# gradle-slimjar-docker-plugin
This plugin create Docker image for Java applications including its runtime jar dependencies and not generating a fatjar 
to produce Docker images that have different layers only for the real code changes, dependencies bundled in a fatjar are
not real changes.

# why
Bundled dependencies in fatjar make the application artifact gross and on every change on our code this artifact is 
different and will be stored in a new layer. But not always is all new, fatjar contains unchanged dependencies they are 
not real changes and should be on a different docker layer from your source code.

there are some literature about that deep explain the issue:
- https://phauer.com/2019/no-fat-jar-in-docker-image/
- https://medium.com/holisticon-consultants/dont-build-fat-jars-for-docker-applications-6252a5571248

# how to use the plugin
Add it to your build.gradle on plugins area. Check last version to use at [Gradle Plugin page](https://plugins.gradle.org/plugin/info.developia.gradle.docker.slimjar):
```
plugins {
    id 'info.developia.gradle.docker.slimjar' version '1.0.0'
}
```
By default plugin will use your 'rootProject.name' and 'version' to build image name, but you can override it by adding to build.gradle:
```
docker {
    image 'my-application'
    version '1.0.0'
}
```
