def call() {
    def COLOR_MAP = [
        'FAILURE' : 'danger',
        'SUCCESS' : 'good',
        'UNSTABLE': 'warning',
        'ABORTED' : 'warning'
    ]
    
    echo "Slack Notification"
    slackSend(
        channel: params.slackChannel,
        color: COLOR_MAP[currentBuild.currentResult] ?: 'warning',
        botUser: true,
        message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} \n build ${env.BUILD_NUMBER} \n More info at: ${env.BUILD_URL}"
    )   
}