def call() {
script{
 echo "Slack Notification"
 slackSend(
    channel:params.slackChannel,
    color: COLOR_MAP[currentBuild.currentResult],
    message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} \n build ${env.BUILD_NUMBER} \n More info at: ${env.BUILD_URL}"
 )   
}
}