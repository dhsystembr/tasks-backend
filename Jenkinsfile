pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                sh '/bin/bash mvn clean package -DskipTests=true'
            }
        }
    }
}