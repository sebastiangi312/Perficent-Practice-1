pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                checkout scm
            }
        }
        stage('test') {
            steps {
                sh 'mvn spring-boot:run'
            }
        }
    }
}