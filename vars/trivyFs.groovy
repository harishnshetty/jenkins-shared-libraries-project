def call() {
    sh "trivy fs --format template --template '@html.tpl' -o trivyfs_${env.BUILD_NUMBER}.html ."
}