def call(String dockerHubUsername, String imageName) {
    withDockerRegistry([url: 'https://index.docker.io/v1/', credentialsId: 'docker']) {
        sh "docker push ${dockerHubUsername}/${imageName}:latest"
        sh "docker push ${dockerHubUsername}/${imageName}:${env.BUILD_NUMBER}"
    }
}