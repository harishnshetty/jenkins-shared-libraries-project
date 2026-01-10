def call() {
    sh "git config user.name \"${params.GitUserName}\""
    sh "git config user.email \"${params.GitUserEmail}\""
    sh "git add ."
    sh "git commit -m \"Done by jenkins pipeline ${env.BUILD_NUMBER}\""
    sh "git push https://${params.GitUserConfigName}:${params.GitPassword}@github.com/${params.GitUserName}/${params.GitRepo}.git"
}