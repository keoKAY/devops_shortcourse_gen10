pipeline{
    agent any // any agent that are available (master )
    environment{
        TELEGRAM_TOKEN=""
        CHAT_ID=""
    }
    stages{

        stage("Sending Sucess Telegram"){
            steps{

                script{
                    def message = """
Your websites has **deploy successfully** 🟢
🌍 URL is : https://earthdx.anajak-khmer.site 
🧾 Access Code report: https://sonarqube.anajak-khmer.site/dashboard?id=reactjs-devops10-template  

                    """

                    sendTelegramMessage("${message}","${TELEGRAM_TOKEN}","${CHAT_ID}")
                }
              
      

              
            }
        }
     
    }
}


def sendTelegramMessage(String message, String token, String chatId){
    def encodedMessage = URLEncoder.encode(message, "UTF-8")
    sh """
        curl -s -X POST https://api.telegram.org/bot${token}/sendMessage \\
        -d chat_id=${chatId} \\
        -d text="${encodedMessage}" > /dev/null
    """
}




