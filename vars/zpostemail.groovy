def call(String buildStatus, String emailAddress) {
    emailext(
        subject: "Pipeline ${buildStatus}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
        body: """
            <p>Project: ${env.JOB_NAME}</p>
            <p> Build Name: ${env.JOB_NAME}</p>
            <p>Build Number: ${env.BUILD_NUMBER}</p>
            <p>Status: ${buildStatus}</p>
            <p>Started by: ${env.BUILD_USER}</p>

            <p><a href="${env.BUILD_URL}">View Build</a></p>
        """,
        to: "${emailAddress}",
        from: "${emailAddress}",
        mimeType: 'text/html',
        attachmentsPattern: 'reports/gitleaks-report.json'
    )

}



// mfygsfnjfrweaesx