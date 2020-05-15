pipeline {
    agent { 
        docker { 
            image 'maven:3.3.3'
        }
    }
    environment {
        DB_ENGINE = 'sqlite'
    }
    stages {
        stage('build') {
            steps {
                retry(3) {
	                sh 'mvn --version'
                }
                
                sh 'echo "Hello World"'
                sh 'echo "Database engine is ${DB_ENGINE}"'
                
                timeout(time: 3, unit: 'MINUTES') {
	                sh '''
	                    echo "Multiline shell steps works too"
	                    ls -lah
	                '''
                }
            }
        }
        stage('test') {
            steps {
                junit '**/surefire-reports/*.xml'
            }
        }
    }
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}
