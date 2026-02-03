def call() {
    sh "gitleaks detect --source . -r gitleaks-report.json -f json"
}