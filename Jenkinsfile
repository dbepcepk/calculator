pipeline {
    agent any
    triggers {
        pollSCM('* * * * 5')
    }
    stages {
        stage('Compile') {
            steps {
                sh './gradlew compileJava'
            }
        }
        stage('Unit test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Code coverage') {
            steps {
                sh './gradlew jacocoTestReport'
                publishHTML (target: [
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: 'JaCoCo Report'
                ])
                sh './gradlew jacocoTestCoverageVerification'
            }
        }
        stage('Static code analysis') {
            steps {
                sh './gradlew checkstyleMain'
                publishHTML (target: [
                    reportDir: 'build/reports/checkstyle/',
                    reportFiles: 'main.html',
                    reportName: 'Checkstyle Report'
                ])
            }
        }
        stage('Package') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Docker build') {
            steps {
                sh "docker build -t dbepcepk/calculator ."
            }
        }
        stage('Docker push') {
            steps {
                sh 'docker push dbepcepk/calculator'
            }
        }
        stage('Deploy to staging') {
            steps {
                sh 'docker run -d --rm -p 8765:8080 --name calculator dbepcepk/calculator'
            }
        }
        stage('Acceptance tests') {
            steps {
                sleep 60
                sh './gradlew acceptanceTest -Dcalculator.url=http://localhost:8765'
            }
        }
    }
    post {
        always {
            sh 'docker stop calculator'
        }
    }
}