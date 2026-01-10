def call() {
    sh "trivy image ${env.dockerHubUsername}/${env.dockerImageName}:${env.BUILD_NUMBER} > trivyimage_${env.BUILD_NUMBER}.txt"
}