pipeline {
    agent { docker { image 'maven:3.8.5-openjdk-18-slim' } }
    stages {
        stage('build') {
            steps {
                sh 'docker network create --subnet=122.23.0.0/16 my-network '
                sh 'docker run --name my-postgres --network="my-network" --ip 122.23.0.2 -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres'
                sh 'git clone https://github.com/sebastiangi312/Perficent-Practice-1'
            }
        }
    }
}