def call() {
   withDockerRegistry([url: 'https://index.docker.io/v1/', credentialsId: 'dockerhub-token']) {
        sh "docker push ${env.dockerHubUsername}/${env.dockerImageName}:latest"
        sh "docker push ${env.dockerHubUsername}/${env.dockerImageName}:${env.BUILD_NUMBER}"
    }
}