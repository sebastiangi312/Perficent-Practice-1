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
                sh 'docker image prune --all --force '
                sh(returnStdout: true, script: '''#!/bin/bash
                    if [[ "$(docker container ls | grep backend )" != "" ]] ; then
                        docker stop backend
                        docker rm -f backend
                    fi
                    '''.stripIndent()
                )
                sh(returnStdout: true, script: '''#!/bin/bash
                    if [[ "$(docker ps -a | grep my-postgres )" != "" ]] ; then
                        docker stop my-postgres
                        docker rm -f my-postgres
                    fi
                    '''.stripIndent()
                )
            }
        }

        stage('Cloning Repo') {
            steps {
                checkout scm
            }
        }
        
        stage('Building Image') {
            steps {
                sh 'docker build .'
            }
        }

        stage('Running Postgres and Data'){
            steps {
                sh 'docker-compose run --name backend -d api ./mvnw test'
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