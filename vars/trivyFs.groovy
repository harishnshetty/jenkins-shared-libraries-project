def call() {
    sh """
      trivy fs \
        --format template \
        --template @contrib/html.tpl \
        -o trivyfs_${env.BUILD_NUMBER}.html \
        .
    """
}
