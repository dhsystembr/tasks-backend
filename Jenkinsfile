pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                sh 'echo mvn clean package -DskipTests=true'
            }
        }
    }
}