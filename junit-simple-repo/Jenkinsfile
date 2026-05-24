pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Testes JUnit') {
      steps {
        sh 'mvn clean test'
      }
    }
  }

  post {
    always {
      junit 'target/surefire-reports/*.xml'
      archiveArtifacts artifacts: 'target/surefire-reports/*', allowEmptyArchive: true
    }
  }
}
