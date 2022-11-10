pipeline {
       environment
           {    registry = "mohamedghf/devopsprojet"
                registryCredential = 'docker_id'
                dockerImage = ''
                 NEXUS_VERSION="nexus3"
                 NEXUS_PROTOCOL="http"
                 NEXUS_URL="192.168.1.7:8081"
                 NEXUS_REPOSITORY="maven-snapshots"
                 NEXUS_CREDENTIAL_ID="nexus-user-credentials"


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
          stage("Build"){
                    steps {
                             sh 'mvn clean package'
                              sh 'mvn install package'


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
                   stage("docker compose"){
                                      steps {
                                               sh "docker-compose -f docker-compose.yml up -d  "
                                                     }

                                                           }
         stage('SonarQube analysis 1') {
                  steps {
                              sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=203JMT4407'
                            }
                            }

          stage("nexus deploy"){
                    steps {
                                  sh 'mvn deploy'

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
                              stage('Cleaning up') {
                                                   steps {
                                                             sh "docker rmi $registry:$BUILD_NUMBER"
                                                   }
                                             }


                             }
                             }
