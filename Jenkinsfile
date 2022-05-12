pipeline {
    agent any
    tools{
        dockerTool  'mydocker'
    }
    stages {
        
        stage('Creating Subnets and Pulling Repo') {
            steps {
                checkout scm
                docker network create --subnet=122.23.0.0/16 my-network 
            }
        }
    }
}