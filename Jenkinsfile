pipeline{
    agent any

    stages{


        stage('Cloning from GitHub') {
                steps {
                    git branch: 'Yassine', url: 'https://github.com/MedAmineKadri/ProjetDevops.git'
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
        stage('Build Docker Image') {
            steps {
                script {
                        sh """ docker build -t nakbiyassine/achat ."""
                    
                  
                }
            }
        }       
        
        stage('Login') {
            steps{
                
                sh """ docker login -u "nakbiyassine" -p "yassine123@" docker.io  """
            }
        }
        
        
        stage('push to DockerHub') {
            steps{

                sh """ docker push  nakbiyassine/achat """
                
            }
        }
        
       
      
        

        
        
        
        
        
        
       
        
        
       


    }
}
