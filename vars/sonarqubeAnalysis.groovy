def call() {
    withSonarQubeEnv('sonar-server') {
        sh """ $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=${env.projectName} -Dsonar.projectKey=${env.projectKey} """
    }
}
