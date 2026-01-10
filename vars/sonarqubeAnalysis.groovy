def call(String projectName, String projectKey) {
    withSonarQubeEnv('sonar-server') {
        sh """ $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=${projectName} -Dsonar.projectKey=${projectKey} """
    }
}
