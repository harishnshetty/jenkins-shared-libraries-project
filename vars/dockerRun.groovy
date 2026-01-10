def call() {
    sh "docker rm -f $env.dockerImageName || true"
    sh "docker run -d --name $env.dockerImageName -p $EXPOSE_PORT:$CONTAINER_PORT $env.dockerHubUsername/$env.dockerImageName:$env.${env.BUILD_NUMBER}"
}