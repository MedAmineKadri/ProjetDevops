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

               stage('Cleaning up') {
                     steps {
                               sh "docker rmi $registry:$BUILD_NUMBER"
                     }
               }
               stage("nexus deploy"){
                                       steps {
                                          sh 'mvn deploy'

                                             }
                                }
                stage("docker compose"){
                                                                    steps {
                                                                        sh 'mvn clean package'
                                                                       // sh 'sudo chmod 666 /var/run/docker.sock'
                                                                        sh 'docker-compose up -d --build'


                                                                    }

                                          }


                             }
                             }
