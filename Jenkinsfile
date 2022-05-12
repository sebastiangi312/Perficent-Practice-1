pipeline {
    agent{
        docker {
            image 'maven:3.8.5-openjdk-18-slim'
        }
    }
    tools{
        dockerTool  'mydocker'
    }
    stages {
        
        stage('Creating Subnets and Cloning Repo') {
            steps {
                checkout scm
                //sh 'docker network create --subnet=122.23.0.0/16 my-network '
            }
        }
        
        stage('Running Postgres and testing Code'){
            steps {
                //sh 'docker run --name my-postgres --network="my-network" --ip 122.23.0.2 -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres'
                sh 'mvn spring-boot:run'
            }
        }
        stage('Building image and pushing'){
            steps {
                sh 'docker -v'
                //sh 'docker build -t my_back .'
                //sh 'docker run --name backend --network="my-network" --ip 122.23.0.3 -p 8081:8081 -d my_back'
            }
        }
    }
}