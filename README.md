# gradle-plugin-docker

gradle-plugin-docker is a custom gradle plugin for integrating with Docker.

## Gradle Tasks

```
Docker tasks
------------
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
Clone the repository and execute `gradlew build`.  This will build the plugin jar and place it in the `build/libs` directory.

Use the local plugin in your own project by setting the following in your project's build file.

```
buildscript {
    dependencies {
        classpath files('[YOUR PATH]/gradle-plugin-docker/build/libs/gradle-plugin-docker-[LATEST VERSION].jar')
    }
}
apply plugin: com.sidneysimmons.gradleplugindocker.DockerPlugin
```

## License
[MIT](https://choosealicense.com/licenses/mit/)