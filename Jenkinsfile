pipeline {

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
                             }
                             }
