pipeline {
    agent {
        label 'ssh-agent'
    }

    stages {
        stage('Hello') {
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