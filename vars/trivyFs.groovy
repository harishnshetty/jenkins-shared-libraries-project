def call() {
    sh "trivy fs --format template --template resources/html.tpl -o trivyfs_${env.BUILD_NUMBER}.html ."
}