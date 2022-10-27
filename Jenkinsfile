pipeline{
    agent any

    stages{


        stage('Cloning from GitHub') {
                steps {
                    git branch: 'samar', url: 'https://github.com/trifieya/Continuous-integration-project.git'
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
