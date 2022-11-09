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
        stage('upload war file to nexus') {
            steps {
                script{
                    nexusArtifactUploader artifacts:
                    [
                        [
                            artifactId: 'achat',
                            classifier: '', file: 'target/achat-1.0.1.jar',
                            type: 'jar'
                        ]
                    ], 
                    credentialsId: 'nexus-api-auth-class',
                    groupId: 'tn.esprit.rh',
                    nexusUrl: '192.168.1.7:8081',
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    repository: 'achat-snapshot',
                    version: '1.0.1'
                }
                
            }
        }   
    }     
}