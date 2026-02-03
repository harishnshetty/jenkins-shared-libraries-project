def call() {
    sh "trivy image --format template --template '@html.tpl' -o trivyimage_${env.BUILD_NUMBER}.html ${env.dockerHubUsername}/${env.dockerImageName}:${env.BUILD_NUMBER}"
}


