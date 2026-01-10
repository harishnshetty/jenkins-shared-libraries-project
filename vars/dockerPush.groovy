def call() {
   withCredentials([usernamePassword(credentialsId: 'dockerhub-token', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
        sh "docker push ${env.dockerHubUsername}/${env.dockerImageName}:latest"
        sh "docker push ${env.dockerHubUsername}/${env.dockerImageName}:${env.BUILD_NUMBER}"
    }
}