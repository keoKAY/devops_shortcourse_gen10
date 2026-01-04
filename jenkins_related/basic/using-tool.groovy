
pipeline {
    agent any

    tools{ 
        nodejs 'nodejs18-lts'
    }

    stages {
        stage('Clone Code') {
            steps {
                git "https://github.com/sexymanalive/reactjs-devop10-template"
                sh "ls -lrt"
            }
        }
        stage('Use NPM build'){
            steps{

                sh """
                node --version 
                npm --version 

                npm install 
                npm run build 

                """
            }
        }
    }
}

