# gradle-slimjar-docker-plugin
This plugin create Docker image for Java applications including its runtime jar dependencies and not generating a fatjar 
to produce Docker images that have different layers only for the real code changes, dependencies bundled in a fatjar are
not real changes.

# why
Bundled dependencies in fatjar make the application artifact gross and on every change on our code this artifact is 
different and will be stored in a new layer. But not always is all new, fatjar contains unchanged dependencies they are 
not real changes and should be on a different docker layer from your source code.

https://www.google.com/search?q=no+fatjar+in+docker&rlz=1C5GCEM_en&oq=no+fatjar+in+docker&aqs=chrome..69i57.7304j0j7&sourceid=chrome&ie=UTF-8
https://phauer.com/2019/no-fat-jar-in-docker-image/
https://medium.com/holisticon-consultants/dont-build-fat-jars-for-docker-applications-6252a5571248
