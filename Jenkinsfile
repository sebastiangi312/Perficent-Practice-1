pipeline {
    agent any
    tools{
        dockerTool  'mydocker'
    }
    stages {
        
        stage('Creating Subnets and Cloning Repo') {
            steps {
                checkout scm
                sh(returnStdout: true, script: '''#!/bin/bash
                    if [[ "$(docker network ls | grep my-network )" == "" ]] ; then
                        docker network create --subnet=122.23.0.0/16 my-network 
                    fi
                    '''.stripIndent()
                )
            }
        }
        
        stage('Building Image') {
            steps {
                sh 'docker build -t "my_back" --target build .'
            }
        }

        stage('Running Postgres and testing Code'){
            steps {
                sh 'docker run --name my-postgres -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres'
                sh 'docker run --rm --network="my-network" --ip 122.23.0.3 -p 8081:8081 -e DB_URL=122.23.0.2:5432 --name  springboot-test "my_back" mvn test'
            }
        }
        stage('Pushing'){
            steps {
                sh 'docker -v'
                //sh 'docker build -t my_back .'
                //sh 'docker run --name backend --network="my-network" --ip 122.23.0.3 -p 8081:8081 -d my_back'
            }
        }
    }
}