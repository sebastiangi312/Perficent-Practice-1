pipeline {
    agent {
        docker { image 'maven:3.8.5-openjdk-18-slim' }
    }
    stages {
        stage('build') {
            steps {
                checkout scm
            }
        }
        stage('test') {
            steps {
                sh 'mvn install'
            }
        }
    }
}