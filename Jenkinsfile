pipeline {
    agent any
    tools{
        dockerTool  'mydocker'
    }
    environment  {
        DOCKERHUB_CREDENTIALS = credentials('segiraldovi-Dockerhub')
    }
    stages {
        
        stage('Cleaning previous containers') {
            steps {
                sh '''
                  docker stop backend_ut || true
                  docker container prune -f
               '''
               sh '''
                  docker image rm -f backend_ut || true
               '''
            }
        }

        stage('Cloning Repo') {
            steps {
                checkout scm
            }
        }
        
        stage('Building Image') {
            steps {
                sh 'docker-compose build'
            }
        }

        stage('Running Tests'){
            steps {
                sh 'docker-compose run api ./mvnw test'
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