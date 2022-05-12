pipeline {
    agent { docker { image 'maven:3.8.5-openjdk-18-slim' } }
    stages {
        stage('build') {
            steps {
                sh 'docker network create --subnet=122.23.0.0/16 my-network '
            }
        }
    }
}