def call() {
    sh 'sed -i "s/image: .*/image: ${env.dockerHubUsername}/${env.dockerImageName}:${env.BUILD_NUMBER}/" k8s/deployment.yaml'
}