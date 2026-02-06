def call() {

    def buildStatus = currentBuild.currentResult ?: 'SUCCESS'

    slackSend(
        channel: params.slackChannel,
        color: buildStatus == 'SUCCESS' ? 'good' : 'danger',
        message: """
                Job: ${env.JOB_NAME}
                Build: #${env.BUILD_NUMBER}
                Status: ${buildStatus}

                ${env.BUILD_URL}
                """
    )
}
