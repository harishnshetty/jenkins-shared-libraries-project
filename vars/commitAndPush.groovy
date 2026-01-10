def call() {
    withCredentials([
        usernamePassword(
            credentialsId: 'github-creds',
            usernameVariable: 'GIT_USER',
            passwordVariable: 'GIT_TOKEN'
        )
    ]) {
        sh """
            git config user.name "${params.gitUserConfigName}"
            git config user.email "${params.gitUserConfigEmail}"

            git checkout ${params.gitBranch}

            git add .
            git commit -m "Done by Jenkins pipeline ${env.BUILD_NUMBER}" || echo "No changes"

            git push https://${GIT_USER}:${GIT_TOKEN}@github.com/${params.gitUserName}/${params.gitRepo}.git ${params.gitBranch}
        """
    }
}
