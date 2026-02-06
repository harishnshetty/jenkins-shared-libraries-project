def call() {
script{
 echo "Email Notification"
 emailext(
                subject: "Pipeline ${buildStatus}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>Youtube Link :- https://www.youtube.com/@devopsHarishNShetty </p>                                     
                    <p>Flipkart DevSecops CICD pipeline status.</p>
                    <p>Project: ${env.JOB_NAME}</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p>Build Status: ${buildStatus}</p>
                    <p>Started by: ${buildUser}</p>
                    <p>Build URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                """,
                to: '${params.emailAddress}',
                from: '${params.emailAddress}',
                mimeType: 'text/html',
                attachmentsPattern: 'reports/gitleaks-report.json'
                    ) 
}
}
