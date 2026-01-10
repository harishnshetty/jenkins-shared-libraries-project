def call() {
    sh "git config user.name \"${env.gitUserName}\""
    sh "git config user.email \"${env.gitUserEmail}\""
    sh "git add ."
    sh "git commit -m \"Done by jenkins pipeline ${env.BUILD_NUMBER}\""
    sh "git push https://${env.gitUserConfigName}:${env.gitPassword}@github.com/${env.gitUserName}/${env.gitRepo}.git"
}