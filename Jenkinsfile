pipeline {
    agent any
    tools { 
        maven 'mvn3.8.5' 
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