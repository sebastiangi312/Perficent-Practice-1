pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'git clone https://github.com/sebastiangi312/Perficent-Practice-1'
                sh 'ls'
            }
        }
        stage('test') {
            steps {
                sh 'mvn spring-boot:run'
            }
        }
    }
}