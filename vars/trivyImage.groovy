def call(String dockerHubUsername, String imageName) {
    sh "trivy image ${dockerHubUsername}/${imageName}:${env.BUILD_NUMBER} > trivyimage_${env.BUILD_NUMBER}.txt"
}