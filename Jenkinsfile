pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                checkout scm
                mvnHome = tool name: 'maven-3', type: 'maven'
                mvnCMD = "${mvnHome}/bin/mvn"
                sh "${mvnCMD} clean package"
            }
        }
        stage('test') {
            steps {
                sh 'mvn spring-boot:run'
            }
        }
    }
}