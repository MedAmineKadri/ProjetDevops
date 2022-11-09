pipeline {
       environment
           {    registry = "mohamedghf/devopsprojet"
                registryCredential = 'docker_id'
                dockerImage = ''


            }
       agent any


        stages{
            stage('Checkout GIT'){
                steps{
                    echo 'Pulling...';
                    git branch: 'MEDDHAFER',
                    url : 'https://github.com/MedAmineKadri/ProjetDevops.git';
                             }
                             }
         stage('Testing maven') {
                    steps {
                        sh """mvn -version"""

                    }
                }

                stage('Mvn Clean') {
                    steps {
                        sh 'mvn clean'

                    }
                }
                stage('Mvn Compile') {
                    steps {
                        sh 'mvn compile'

                    }
                }



               stage('Cleaning up') {
                     steps {
                               sh "docker rmi $registry:$BUILD_NUMBER"
                     }
               }
               stage("nexus deploy"){
                                       steps {
                                          sh 'mvn deploy -DskipTests'

                                             }
                                }
                                  stage('SonarQube analysis 1') {
                                            steps {
                                                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=203JMT4407'
                                            }
                                        }
                stage("docker compose"){
                                         steps {
                                                           sh "docker-compose -f docker-compose.yml up -d  "
                                                       }

                                          }
         stage('Building our image') {
                           steps {
                                 script {
                             dockerImage = docker.build registry + ":$BUILD_NUMBER"

                                  }
                                }
                               }
                              stage('Deploy our image') {

                            steps {

                               script {

                                   docker.withRegistry( '', registryCredential ) {

                                       dockerImage.push()

                                         }

                                           }

                                                   }

                              }


                             }
                             }
