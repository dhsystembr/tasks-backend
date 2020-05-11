pipeline {
    agent any
    stages {
        stage('Setting the variables values') {
            steps {
                sh 'echo "Compile"'
                sh '''
                    echo "Complicando o pacote"
                    /home/lab1/docker/apache-maven-3.6.3/bin/mvn clean package -DskipTests=true
                '''
            }
        }
        stage('Unit Test') {
            steps {
                sh '''
                    echo "Complicando o pacote"
                    /home/lab1/docker/apache-maven-3.6.3/bin/mvn test
                '''
            }
        }
        stage('Sonar Analysis') {
            environment {
                scannerHome = tool "SONAL_SCANER"
            }
            steps {
                withSonarQubeEnv('Sonar_local'){
                    sh '''
                    echo "Setando variavel"
                    ${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBackEnd -Dsonar.host.url=http://localhost:9001 -Dsonar.login=c0009ad086b71df21e89e2ce1a957f87c2932157 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**/model/**,**Application.java
                '''
                } 
            }
        }
        stage('Quality Gate') {
            steps {
                sh '''
                    echo "Quality Gate"
                '''
                    sleep(10)
                    timeout(time: 1, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                    }
                
            }
        }
        stage('Deploy Back End') {
            steps {
                sh '''
                    echo "Deploy Back End"
                '''
                    deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'  
            }
        }
        stage('API Test') {
            steps {
                sh '''
                    echo "API Test"
                '''
                dir('api-test') {
                	git credentialsId: 'github_login', url: 'https://github.com/dhsystembr/api-test'
                    sh '''
                    	/home/lab1/docker/apache-maven-3.6.3/bin/mvn test
                	'''
                }    
            }
        }
        stage('Deploy Front End') {
            steps {
                sh '''
                    echo "Deploy Front End"
                '''
                dir('frontend') {
                    git credentialsId: 'github_login', url: 'https://github.com/dhsystembr/tasks-frontend'
                    sh '''
                    	/home/lab1/docker/apache-maven-3.6.3/bin/mvn clean package
                	'''
                    deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001')], contextPath: 'tasks-frontend', war: 'target/tasks.war'
                }    
            }
        }
        stage('Deploy PROD') {
            steps {
                sh '''
                    echo "Deploy PROD"
                    docker-compose build
                    docker-compose up -d
                '''
                }    
            }
        }
    }
}