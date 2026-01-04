@Library('my-first-jenkins-lib@master') _ 
pipeline {
    agent any
    environment {
        IMAGE_NAME="jenkins-reactjs-devop10"
        REPO_NAME="lyvanna544"
        TAG="v1.0.${env.BUILD_NUMBER}"

        TELEGRAM_TOKEN=""

        CHAT_ID=""

    }
    stages {
        stage('Clone Code') {
            steps {
                git "https://github.com/sexymanalive/reactjs-devop10-template"
                sh "ls -lrt"
            }
        }
        stage('Scan with sonarqube'){
            environment{
                scannerHome= tool 'sonarqube-scanner' 
            }

            steps{
              script{  
                    def projectKey = 'reactjs-devops10-template' 
                    def projectName = 'ReactjsDevOps10template'
                    def projectVersion = '1.0.0' 

                    scanSonarqubeReactjs(projectName,projectKey,projectVersion)
                    
            }

            }
        
            
        }

        stage('Wait for the quality gate '){
            steps{
                script{
                    def qualityGate = waitForQualityGate(); 
                    if (qualityGate.status != 'OK') {
                        sh """
                        echo "Quality Gate is failed " 
                        """
                        currentBuild.result = 'FAILURE'
                    }else{
                        sh """
                        echo "Quality Gate is passed " 
                        """
                        currentBuild.result='SUCCESS'
                    }
                }
            }
        }
        stage('Build Image'){
            when{
                expression {
                    currentBuild.result == 'SUCCESS'
                }
            }

            steps{

                sh """
                docker build -t ${REPO_NAME}/${IMAGE_NAME}:${TAG} .

                """
            }
        }

        stage("Push to dockerhub "){
                when{
                expression {
                    currentBuild.result == 'SUCCESS'
                }
            }
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
                when{
                expression {
                    currentBuild.result == 'SUCCESS'
                }
            }
            steps{
                sh """
                docker stop reactjs-cont || true 
                docker rm reactjs-cont  || true 
                docker run -dp 3000:8080 --name reactjs-cont ${REPO_NAME}/${IMAGE_NAME}:${TAG}
                """
            }
        }
    }

    post{
        success{
            script{
                def message="""
Your websites has **deploy successfully** 🟢
🌍 URL is : https://earthdx\\.anajak\\-khmer\\.site 
🧾 Access Code report: https://sonarqube\\.anajak\\-khmer\\.site/dashboard?id\\=reactjs\\-devops10\\-template  
                """
                 sendTelegramMD("${message}","${TELEGRAM_TOKEN}","${CHAT_ID}")   

            }
        }
    }
}

