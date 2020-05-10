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
                    timeout(time: 1, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                    }
                '''
            }
        }
    }
}