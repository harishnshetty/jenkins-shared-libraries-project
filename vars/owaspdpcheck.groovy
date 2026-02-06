def call() {
    dependencyCheck(
        odcInstallation: 'dp-check',
        additionalArguments: '''
            --scan .
            --disableYarnAudit
            --disableNodeAudit
        '''
    )

    dependencyCheckPublisher(
        pattern: '**/dependency-check-report-*.xml'
    )
}