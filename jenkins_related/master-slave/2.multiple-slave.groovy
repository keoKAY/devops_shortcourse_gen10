pipeline {
    agent none
    stages {
        stage('Run on Master') {
            agent {
                label 'master'
            }
            steps {
                echo 'Hello World'
                sh """
                curl ifconfig.me 
                whoami 
                pwd 
                ls -lrt 
                
                """
            }
        }
        stage('Run on SSH-agent') {
            agent {
                label 'ssh-agent'
            }
            steps {
                echo 'Hello World'
                sh """
                curl ifconfig.me 
                whoami 
                pwd 
                ls -lrt 
                
                """
            }
        }
    }
}