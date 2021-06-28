import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.DslContext
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

open class BaseBuildType() : BuildType() {

    constructor(init: BuildType.() -> Unit) : this() {

        // enable status widget
        allowExternalStatus = true

        requirements {
            // make sure we aren't scheduled on a Windows agent
            equals("teamcity.agent.jvm.os.name", "Linux")
        }

        vcs {
            // Use the source code from the same VCS root that the Kotlin DSL came from
            root(DslContext.settingsRoot)
        }

        triggers {
            vcs {
                // empty trigger will run on all branches in the vcs root branch specification
            }
        }

        features {
            // post status checks to GitHub, see https://www.jetbrains.com/help/teamcity/commit-status-publisher.html
            commitStatusPublisher {
                vcsRootExtId = "${DslContext.settingsRoot.id}"
                publisher = github {
                    githubUrl = "https://api.github.com/"
                    authType = personalToken {
                        token = "%github.access.token%"
                    }
                }
            }
        }

        init()
    }

}
