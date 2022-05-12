pipeline {
    agent any
    tools { 
        maven 'Maven 3.8.5' 
        jdk 'jdk18' 
    }
    stages {
        stage('build') {
            steps {
                checkout scm
                echo "PATH = ${PATH}"
                echo "M2_HOME = ${M2_HOME}"
            }
        }
        stage('test') {
            steps {
                sh 'mvn install'
            }
        }
    }
}