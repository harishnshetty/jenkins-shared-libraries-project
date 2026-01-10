def call() {
    // Build the Docker image
    sh "docker build -t ${env.imageName} ."
     // Tag the Docker image
    sh "docker tag ${env.imageName} ${env.dockerHubUsername}/${env.imageName}:latest"
    sh "docker tag ${env.imageName} ${env.dockerHubUsername}/${env.imageName}:${env.BUILD_NUMBER}"
    // Push the Docker image 
    
    }

