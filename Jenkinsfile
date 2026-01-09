@Library('Jenkins_shared_library') _
def COLOR_MAP = [
    'FAILURE' : 'danger',
    'SUCCESS' : 'good'
]

pipeline{
    agent any
    parameters {
        choice(name: 'action', choices: 'create\ndelete', description: 'Select action to perform (create/delete).')
        
        string(name: 'DOCKER_HUB_USERNAME', defaultValue: 'sevenajay', description: 'Docker Hub Username')
        string(name: 'IMAGE_NAME', defaultValue: 'youtube', description: 'Docker Image Name')
        
        string(name: 'gitUrl', defaultValue: 'https://github.com/harishnshetty/flipkart-app1.git', description: 'Git URL')
        string(name: 'gitBranch', defaultValue: 'main', description: 'Git Branch')
    }
    tools{
        jdk 'jdk17'
        nodejs 'node20'
    }
    // environment {
    //     SCANNER_HOME=tool 'sonar-scanner'
    // }
    stages{

        stage('Clean Workspace'){
            steps{
                cleanWorkspace()
            }
        }
        stage('checkout from Git'){
            when { expression { params.action == 'create'}}    

            steps{
                checkoutGit(params.gitUrl, params.gitBranch)
            }
        }
//         stage('sonarqube Analysis'){
//         when { expression { params.action == 'create'}}    
//             steps{
//                 sonarqubeAnalysis()
//             }
//         }
//         stage('sonarqube QualitGate'){
//         when { expression { params.action == 'create'}}    
//             steps{
//                 script{
//                     def credentialsId = 'Sonar-token'
//                     qualityGate(credentialsId)
//                 }
//             }
//         }
//         stage('Npm'){
//         when { expression { params.action == 'create'}}    
//             steps{
//                 npmInstall()
//             }
//         }
//         stage('Trivy file scan'){
//         when { expression { params.action == 'create'}}    
//             steps{
//                 trivyFs()
//             }
//         }
//         stage('OWASP FS SCAN') {
//             steps {
//                 dependencyCheck additionalArguments: '--scan ./ --disableYarnAudit --disableNodeAudit', odcInstallation: 'DP-Check'
//                 dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
//             }
//         }
//         stage('Docker Build'){
//         when { expression { params.action == 'create'}}    
//             steps{
//                 script{
//                    def dockerHubUsername = params.DOCKER_HUB_USERNAME
//                    def imageName = params.IMAGE_NAME
                   
//                    dockerBuild(dockerHubUsername, imageName)
//                 }
//             }
//         }
//         stage('Trivy iamge'){
//         when { expression { params.action == 'create'}}    
//             steps{
//                 trivyImage()
//             }
//         }
//         stage('Run container'){
//         when { expression { params.action == 'create'}}    
//             steps{
//                 runContainer()
//             }
//         }
//         stage('Remove container'){
//         when { expression { params.action == 'delete'}}    
//             steps{
//                 removeContainer()
//             }
//         }
//         stage('Kube deploy'){
//         when { expression { params.action == 'create'}}    
//             steps{
//                 kubeDeploy()
//             }
//         }
//         stage('kube deleter'){
//         when { expression { params.action == 'delete'}}    
//             steps{
//                 kubeDelete()
//             }
//         }
//     }
//     post {
//     always {
//         echo 'Slack Notifications'
//         slackSend (
//             channel: '#channel name',   #change your channel name
//             color: COLOR_MAP[currentBuild.currentResult],
//             message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} \n build ${env.BUILD_NUMBER} \n More info at: ${env.BUILD_URL}"
//         )
//     }
// }
}
}