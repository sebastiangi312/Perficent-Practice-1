pipeline {
    agent { docker { image 'maven:3.8.5-openjdk-18-slim' } }
    stages {
        stage('build') {
            steps {
                sh 'git clone https://github.com/sebastiangi312/Perficent-Practice-1'
            }
        }
        stage('test') {
            sh 'cd Perficent-Practice-1'
            sh 'mvn spring-boot:run'
        }
    }
}