def call() {
    stage('Trivy FS Scan') {
        steps {
            script {
                // Load HTML template from shared library
                def htmlTemplate = libraryResource 'html.tpl'

                // Write it to workspace
                writeFile file: 'trivy-html.tpl', text: htmlTemplate
            }

            sh '''
              trivy fs . \
                --security-checks vuln \
                --severity HIGH,CRITICAL \
                --ignore-unfixed \
                --format template \
                --template @trivy-html.tpl \
                -o trivyfs-report.html || true
            '''
        }
    }
}
