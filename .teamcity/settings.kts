import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubConnection
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.2"

project {

    vcsRoot(HttpsGithubComVikloshSpringPetclinicGitRefsHeadsMain)

    buildType(Build)

    features {
        githubConnection {
            id = "PROJECT_EXT_3"
            displayName = "GitHub.com"
            clientId = "cc73cc60d4304194db28"
            clientSecret = "credentialsJSON:26bf7e39-05cf-4c30-b0e7-427e8189ad36"
        }
    }
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(HttpsGithubComVikloshSpringPetclinicGitRefsHeadsMain)
    }

    steps {
        maven {
            name = "package"
            goals = "package"
        }
    }

    triggers {
        vcs {
        }
    }
})

object HttpsGithubComVikloshSpringPetclinicGitRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/viklosh/spring-petclinic.git#refs/heads/main"
    url = "https://github.com/viklosh/spring-petclinic.git"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "vloshchev@mail.ru"
        password = "credentialsJSON:4a111f43-9eb9-4b43-bcf6-dc8e49f91fe8"
    }
})