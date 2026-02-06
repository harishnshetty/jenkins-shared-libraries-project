def call() {
    withCredentials([usernamePassword(credentialsId: 'github-token', usernameVariable: 'gitUserConfigName', passwordVariable: 'gitUserConfigEmail')]) {
    sh """
    git config --local user.name ${env.gitUserConfigName}
    git config --local user.email ${env.gitUserConfigEmail}
    git checkout ${env.BRANCH}
    sed -i 's|image: .*|image: ${env.dockerHubUsername}/${env.dockerImageName}:${env.BUILD_NUMBER}|' ${env.MANIFESTFILENAME}
    git add ${env.MANIFESTFILENAME} || true
    git commit -m "${env.BUILD_NUMBER}" || true
    git push origin ${env.BRANCH} || true
    """
    }
}