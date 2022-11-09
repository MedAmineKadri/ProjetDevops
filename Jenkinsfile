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
        stage('Maven Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Maven Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Unit Testing') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Integration Testing') {
            steps {
                sh 'mvn verify -DskipUnitTests'
            }
        }
         stage('StaticAnalytic') {
            steps {
               script{
                    withSonarQubeEnv(credentialsId: 'sonar-api-key') {
                         sh """mvn sonar:sonar -DskipTests \
                            -Dsonar.language=java 
                           
                            
                        """
                    }
                }
            }
        }    
    }     
}