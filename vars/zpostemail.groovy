def call(buildStatus, emailAddress) {

    def buildUser = currentBuild.getBuildCauses('hudson.model.Cause$UserIdCause')[0]?.userId ?: 'GitHub User'

    emailext(
        subject: "Pipeline ${buildStatus}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
        body: """
            <p>Flipkart DevSecOps CICD pipeline status.</p>

            <p>Project: ${env.JOB_NAME}</p>
            <p>Build Number: ${env.BUILD_NUMBER}</p>
            <p>Status: ${buildStatus}</p>
            <p>Started by: ${buildUser}</p>

            <p><a href="${env.BUILD_URL}">View Build</a></p>
        """,
        to: '${params.emailAddress}',
        from: '${params.emailAddress}',
        mimeType: 'text/html',
        attachmentsPattern: 'reports/gitleaks-report.json'
    )

}



// mfygsfnjfrweaesx