
pipeline {
    agent any
    stages {
        stage('Clone Code') {
            steps {
                git "https://gitlab.com/keoKAY/reactjs17-templates"
                sh "ls -lrt"
            }
        }
        stage('Build Image'){
            steps{

                sh """
                docker build -t jenkins-reactjs-glab-img .

                """
            }
        }

        stage("Run service"){
            steps{
                sh """
                docker stop reactjs-cont || true 
                docker rm reactjs-cont  || true 
                docker run -dp 3000:8080 --name reactjs-cont jenkins-reactjs-glab-img
                """
            }
        }
    }
}

