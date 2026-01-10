def call() {
   withDockerRegistry([url: 'https://registry.hub.docker.com', credentialsId: 'dockerhub-token']) {
        sh "docker login -u ${env.dockerHubUsername} -p ${env.dockerHubPassword}"   
        sh "docker push ${env.dockerHubUsername}/${env.dockerImageName}:latest"
        sh "docker push ${env.dockerHubUsername}/${env.dockerImageName}:${env.BUILD_NUMBER}"
    }
}