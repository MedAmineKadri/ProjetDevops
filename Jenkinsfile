pipeline {
    agent any

    stages {
         stage('Git Chekout') {
            steps {
                git branch: 'bilel', url: 'https://github.com/MedAmineKadri/ProjetDevops.git'
            }
        }
        stage('Maven Clean') {
            steps {
                sh 'mvn clean'
            }
        }
    }     
}