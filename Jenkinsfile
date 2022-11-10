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
                    withSonarQubeEnv(credentialsId: 'sonar-key-class') {
                         sh """mvn sonar:sonar -DskipTests \
                            -Dsonar.language=java 
                           
                            
                        """
                    }
                }
            }
        } 
        /* stage('upload war file to nexus') {
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
         stage('Docker Image') {
            steps {
                script{
                    sh 'docker image build -t $JOB_NAME:v1.$BUILD_ID .'
                    sh 'docker image tag $JOB_NAME:v1.$BUILD_ID bilelgasmi/$JOB_NAME:v1.$BUILD_ID'
                    sh 'docker image tag $JOB_NAME:v1.$BUILD_ID bilelgasmi/$JOB_NAME:latest'
                }
            }
        }
        stage(' Push Image to the DockerHub') {
            steps {
                script{
                    withCredentials([string(credentialsId: 'dockerhub_Cred_pass', variable: 'dockerhub_cred')]) {
                        sh 'docker login -u bilelgasmi -p ${dockerhub_cred}'
                        sh 'docker image push bilelgasmi/$JOB_NAME:v1.$BUILD_ID'
                        sh 'docker image push bilelgasmi/$JOB_NAME:latest'
                    }
                }    
            }
        }
        stage('Docker Compose') {
            steps {
                sh 'docker-compose up -d --build'
            }
        }      */
    }     
}