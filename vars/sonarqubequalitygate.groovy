def call(String sonarqubeCredentialsId) {
    scannerSonarqubeQualityGate(sonarqubeCredentialsId)
    timeout(time: 3, unit: 'MINUTES') {
    script{
        def qualityGate = waitForQualityGate()
        if (qualityGate.status != 'OK') {
            error "SonarQube quality gate failed: ${qualityGate.status}"
        }
    }
}
}