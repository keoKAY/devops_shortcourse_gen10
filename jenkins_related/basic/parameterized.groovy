
// If user choose RUN_TEST=true, it will run the test ! 
pipeline {
    agent any
 
       tools{ 
        nodejs 'nodejs18-lts'
    }

    environment {
        IMAGE_NAME="jenkins-reactjs-devop10"
        REPO_NAME="lyvanna544"
        TAG="v1.0.${env.BUILD_NUMBER}"
    }
    stages {
        stage('Clone Code') {
            steps {
                git "https://github.com/sexymanalive/reactjs-devop10-template"
                sh "ls -lrt"
            }
        }

        stage("Run Test"){
          when {
                expression {
                    params.RUN_TEST == true 
                }
            }
            steps{
                sh """
                npm install 
                npm  test 

                """
            }
        }
        stage('Build Image'){
            steps{

                sh """
                docker build -t ${REPO_NAME}/${IMAGE_NAME}:${TAG} .

                """
            }
        }

        stage("Push to dockerhub "){
            steps{
                withCredentials([usernamePassword(credentialsId: 'DOCKERHUB-CRED', passwordVariable: 'PASSCODE', usernameVariable: 'USERNAME')]) {

                sh """
                echo ${PASSCODE} | docker login -u ${USERNAME} --password-stdin

                docker push  ${REPO_NAME}/${IMAGE_NAME}:${TAG}
                """
 
}
            }
        }

        stage("Run service"){
            steps{
                sh """
                docker stop reactjs-cont || true 
                docker rm reactjs-cont  || true 
                docker run -dp 3000:8080 --name reactjs-cont ${REPO_NAME}/${IMAGE_NAME}:${TAG}
                """
            }
        }
    }
}

