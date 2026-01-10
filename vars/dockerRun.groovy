def call() {
    sh 'docker rm -f $params.imageName || true'
    sh 'docker run -d --name $params.imageName -p $EXPOSE_PORT:$CONTAINER_PORT $params.dockerHubUsername/$params.imageName:$params.${env.BUILD_NUMBER}'
}