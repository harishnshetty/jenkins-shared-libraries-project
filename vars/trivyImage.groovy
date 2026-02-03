def call() {
    sh """
      trivy image \
        --format template \
        --template @contrib/html.tpl \
        -o trivyimage_${env.BUILD_NUMBER}.html \
        ${env.dockerHubUsername}/${env.dockerImageName}:${env.BUILD_NUMBER}
    """
}
