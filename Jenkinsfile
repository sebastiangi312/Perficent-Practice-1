pipeline {
    agent any
    tools{
        dockerTool  'mydocker'
    }
    environment  {
        DOCKERHUB_CREDENTIALS = credentials('segiraldovi-Dockerhub')
    }
    stages {
        
        stage('Cleaning previous Test') {
            steps {
                sh(returnStdout: true, script: '''#!/bin/bash
                    if [[ "$(docker container ls | grep backend )" != "" ]] ; then
                        docker stop backend
                        docker rm backend
                    fi
                    '''.stripIndent()
                )
                
                sh(returnStdout: true, script: '''#!/bin/bash
                    if [[ "$(docker container ls | grep segiraldovi/backend )" != "" ]] ; then
                        docker stop segiraldovi/backend
                        docker rm segiraldovi/backend
                    fi
                    '''.stripIndent()
                )

                sh(returnStdout: true, script: '''#!/bin/bash
                    if [[ "$(docker container ls | grep my-postgres )" != "" ]] ; then
                        docker stop my-postgres
                        docker rm my-postgres
                    fi
                    '''.stripIndent()
                )
            }
        }

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
                sh 'docker build -t segiraldovi/backend --target build .'
            }
        }

        stage('Running Postgres and testing Code'){
            steps {
                sh 'docker run --rm --network="my-network" --ip 122.23.0.3 -p 8081:8081 -e DB_URL=group5-rds.cqqmj66dxtlw.us-east-1.rds.amazonaws.com:5432 -e POSTGRES_PORT=8080 --name  backend "segiraldovi/backend" mvn test'
            }
        }
        stage('Pushing'){
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin '
                sh 'docker push segiraldovi/my_back:latest'
            }
        }
    }
    post {
        always {
        sh 'docker logout'
        }
    }
}