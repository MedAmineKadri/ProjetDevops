pipeline{
    agent any

    stages{


        stage('Cloning from GitHub') {
                steps {
                    git branch: 'yassine', url: 'https://github.com/MedAmineKadri/ProjetDevops.git'
                }
                
            }
      
      stage('Clean'){
            steps {
                sh 'mvn clean '
            }
            
        }
        
        stage('Compile'){
            steps {
                sh 'mvn compile -DskipTests'
            }
            
        }
        stage('SonarQube Analysis'){
                steps {
                    sh """mvn sonar:sonar -DskipTests \
                            -Dsonar.language=java 
                           
                            
                    """
                }
                
            }
        
        
        
         stage('UNIT test'){
            steps{
                sh 'mvn test'
            }
        }

         
        
        
        
        stage('Nexus'){
            steps{
                sh 'mvn deploy -DskipTests'
            }
        }
        
        
       
      
        

        
        
        
        
        
        
       
        
        
       


    }
}
