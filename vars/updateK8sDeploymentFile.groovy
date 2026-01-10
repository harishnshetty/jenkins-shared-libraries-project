def call() {
    sh 'sed -i "s/image: .*/image: ${params.dockerHubUsername}/${params.imageName}:${params.${env.BUILD_NUMBER}}/" k8s/deployment.yaml'
}