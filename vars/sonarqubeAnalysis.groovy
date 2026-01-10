def call() {
    withSonarQubeEnv('sonar-server') {
        sh """ $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=${params.projectName} -Dsonar.projectKey=${params.projectKey} """
    }
}
