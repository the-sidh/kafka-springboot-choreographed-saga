rootProject.name = "travel-plane-ticket"

pluginManagement {
    val artifactoryRepository: String by settings
    val artifactoryUser: String by settings
    val artifactoryPassword: String by settings

    repositories {
        maven {
            setUrl(artifactoryRepository)
            credentials {
                username = artifactoryUser
                password = artifactoryPassword
            }
        }
        mavenLocal()
    }
}
