pipeline {
    agent any
    stages {
        stage('Setting the variables values') {
            steps {
                sh 'echo "Compile"'
                sh '''
                    echo "Complicando o pacote"
                    /home/lab1/docker/apache-maven-3.6.3/bin/mvn clean package
                '''
            }
        }
    }
}