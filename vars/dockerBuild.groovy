def call(String dockerHubUsername, String imageName) {
    // Build the Docker image
    sh "docker build -t ${imageName} ."
     // Tag the Docker image
    sh "docker tag ${imageName} ${dockerHubUsername}/${imageName}:latest"
    sh "docker tag ${imageName} ${dockerHubUsername}/${imageName}:${env.BUILD_NUMBER}"
    // Push the Docker image 
    
    }

