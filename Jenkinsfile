import java.text.SimpleDateFormat
pipeline{
environment {
        registry = "rafedchraiti/devopsrafed"
        registryCredential = 'dckr_pat_wadd9m36GOJGduKhbJig6Pf458Q'
        dockerImage = ''
    }
    agent any
    stages{
        stage("Checkout git"){
                                steps{
                                  git branch: 'RafedBackend',
                                   url: 'https://github.com/MedAmineKadri/ProjetDevops';
                               }
                           }
        stage('Date'){
                                 steps {
                                    script{
                                            def date = new Date()
                                            sdf = new SimpleDateFormat("MM/dd/yyyy")
                                            println(sdf.format(date))
                                           }
                                        }
                      }
        stage("MVN Clean + Compile + Package"){
                                steps{
                                            sh 'mvn clean'
                                            sh 'mvn compile'
                                            sh 'mvn package'
                                }
                        }

        /*stage("MVN Clean"){
                                steps{
                                    sh 'mvn clean'
                                }
        }
        stage('MVN compile'){
                                steps{
                                    sh 'mvn compile'
                                }
        }
        stage('MVN package'){
                                steps{
                                    sh 'mvn package'
                                }
        }*/
        stage("Test JUnit - Mockito"){
                                steps {
                                    sh 'mvn test'
                                }
        }
        stage('SonarQube CodeQuality'){
                                steps{
                                    sh  'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=41120725'
                                }
        }
        stage("NEXUS"){
                       steps{
                               sh 'mvn deploy:deploy-file -DgroupId=tn.esprit.rh -DartifactId=achat -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.1.148:8081//repository/achatintegration -Dfile=target/devopsproject.jar'
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
                                    dockerImage.push()}
                                            }
                                }
        }
        stage('Cleaning up') {
                                steps {
                                sh "docker rmi $registry:$BUILD_NUMBER"
                                }
        }
        /*stage('DOCKER COMPOSE') {
                        steps {
                                    sh 'docker-compose up -d --build'
                        }
        }*/
    }
    post {

                        success {
                            mail to: "rafed.chraiti@esprit.tn",
                            body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n, More info at: ${env.BUILD_URL}",
                            from: 'rafed.chraiti@esprit.tn',
                            subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
                        }

                        failure{
                            mail to: "rafed.chraiti@esprit.tn",
                            subject: "Jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
                            from: 'rafed.chraiti@esprit.tn',
                            body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}"
                        }

                        changed{
                            mail to: "rafed.chraiti@esprit.tn",
                            subject: "Jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
                            from: 'rafed.chraiti@esprit.tn',
                            body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}"
                        }
                    }
}