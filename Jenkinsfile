pipeline {
    agent any
    tools{
        dockerTool  'mydocker'
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