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
                    ${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBackEnd -Dsonar.host.url=http://localhost:9001 -Dsonar.login=e50f641085e979346b003ba826bedd45e87a3530 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**/model/**,**Application.java
                '''
                } 
            }
        }
        stage('Quality Gate') {
            steps {
                sh '''
                    echo "Quality Gate"
                '''
                    sleep(5)
                    timeout(time: 1, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                    }
                
            }
        }
        stage('Deploy Back End') {
            steps {
                sh '''
                    echo "Quality Gate"
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
    }
}