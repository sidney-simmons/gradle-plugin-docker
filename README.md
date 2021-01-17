# gradle-plugin-docker (archived)

*Archived and no longer in active development.*

gradle-plugin-docker is a custom gradle plugin for integrating with Docker.

## Gradle Tasks

```
Docker tasks
------------
dockerComposeBuild - Run docker-compose build.
dockerComposeDown - Run docker-compose down.
dockerComposeUp - Run docker-compose up.
dockerComposeVersion - Prints system docker-compose version.
dockerVersion - Prints system docker version.
```

## Configuration

``` gradle
docker {
    // Sets the name of an optional docker machine
    machineName = 'my-docker-machine'
    
    // Sets extra environment variables
    environment('POSTGRES_USER', 'my-pg-user')
    environment('POSTGRES_PASSWORD', 'my-pg-password')
    
    // Tells docker you want a certain number of instances for a
    // given service (see --scale)
    scale('manager-service', 2)
    scale('worker-service', 3)
}
```

| Property | Type | Description |
| --- | --- | --- |
| machineName | String | The name of the docker-machine with which to run commands against. Required for some earlier versions of Windows that are using Docker Toolbox. Optional. |

| Method | Input | Description |
| --- | --- | --- |
| environment | String key, String value | Add a key/value pair to the map of extra environment variables. Optional. These extra environment variables are included in the docker commands. For example - they can be used in a docker-compose.yml file as ${POSTGRES_USER} |
| scale | String service, Integer numberOfNodes | Tells Docker to scale a particular service to the given number of instances. |

## Applying the Plugin

Use the published plugin by setting the following in your project's build file.  You can find the latest version [here](https://plugins.gradle.org/plugin/com.sidneysimmons.gradle-plugin-docker).

``` gradle
plugins {
    id 'com.sidneysimmons.gradle-plugin-docker' version '[LATEST VERSION]'
}
```

## Publishing the Plugin

1. Increment the "version" within build.gradle.

2. Run the following to publish.

```
./gradlew -Dgradle.publish.key=${PUBLISH_KEY} -Dgradle.publish.secret=${PUBLISH_SECRET} publishPlugins
```

3. Add a new release to the GitHub releases.

## License
[MIT](https://choosealicense.com/licenses/mit/)