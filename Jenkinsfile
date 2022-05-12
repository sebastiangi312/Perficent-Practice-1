pipeline {
    agent any
    tools{
        dockerTool  'mydocker'
    }
    stages {
        
        stage('Creating Subnets and Pulling Repo') {
            steps {
                checkout scm
                //sh 'docker network create --subnet=122.23.0.0/16 my-network '
            }
        }
        
        stage('Running Postgres'){
            docker run --name my-postgres --network="my-network" --ip 122.23.0.2 -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres
        }
    }
}