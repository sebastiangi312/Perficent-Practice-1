pipeline {
    agent any
    tools{
        docker  'mydocker'
    }
    stages {
        
        stage('build') {
            steps {
                checkout scm
                sh 'docker -v '
            }
        }
    }
}