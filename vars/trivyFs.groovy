def call() {
    sh 'trivy fs . > trivyfs_${env.BUILD_NUMBER}.txt'
}