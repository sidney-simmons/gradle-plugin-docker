# gradle-plugin-docker

gradle-plugin-docker is a custom gradle plugin for integrating with Docker.

## Gradle Tasks

```
Docker tasks
------------
dockerComposeDown - Run docker-compose down.
dockerComposeUp - Run docker-compose up.
dockerComposeVersion - Prints system docker-compose version.
dockerVersion - Prints system docker version.
```

## Usage

#### Via Gradle Plugin Portal

Use the published plugin by setting the following in your project's build file.  You can find the latest version [here](https://plugins.gradle.org/plugin/com.sidneysimmons.gradle-plugin-docker).

```
plugins {
    id 'com.sidneysimmons.gradle-plugin-docker' version '[LATEST VERSION]'
}
```

#### Via Local Build
Clone the repository and execute `gradlew build`.  Then execute `gradlew publish`.  This will publish the plugin to a local maven repository directory `[PLUGIN PROJECT ROOT]/build/test-maven-repo`.  Now you can use the local plugin in your own project by setting the following in your project's build files.

> build.gradle

```
plugins {
    id 'com.sidneysimmons.gradle-plugin-docker' version '[PLUGIN VERSION]'
}
```

> settings.gradle

```
pluginManagement {
    repositories {
        maven {
            url '[PLUGIN PROJECT ROOT]/build/test-maven-repo'
        }
        gradlePluginPortal()
    }
}
```

## License
[MIT](https://choosealicense.com/licenses/mit/)