def call() {

    // Run OWASP Dependency Check scan
    dependencyCheck(
        odcInstallation: 'dp-check',
        additionalArguments: '''
            --scan .
            --format XML
            --out .
            --disableYarnAudit
            --disableNodeAudit
        '''
    )
    // --format HTML

    // Publish results (Jenkins Trend Dashboard)
    dependencyCheckPublisher(
        pattern: '**/dependency-check-report.xml'
    )

    // Publish HTML report inside Jenkins
    // publishHTML([
    //     allowMissing: false,
    //     alwaysLinkToLastBuild: true,
    //     keepAll: true,
    //     reportDir: '.',
    //     reportFiles: 'dependency-check-report.html',
    //     reportName: 'OWASP Dependency Check Report'
    // ])
}
