Jenkinsfile (Declarative Pipeline)
pipeline {
    agent { docker { image 'segiraldovi/my_back' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}