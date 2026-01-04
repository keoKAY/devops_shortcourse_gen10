@Library('my-first-jenkins-lib@master') _ 
pipeline {
    agent any
    environment{
        TELEGRAM_TOKEN="your-token-here"

        CHAT_ID="your-chat-id-here"
    }
    stages {
        stage('Stage1') {
            steps {
                echo 'Hello World Stage1'
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


