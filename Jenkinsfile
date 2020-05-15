pipeline {
    agent { 
        docker { 
            image 'maven:3.3.3'
        }
    }
    
    stages {
        stage('build') {
            steps {
                retry(3) {
	                sh 'mvn --version'
                }
                
                sh 'echo "Hello World"'
                
                timeout(time: 3, unit: 'MINUTES') {
	                sh '''
	                    echo "Multiline shell steps works too"
	                    ls -lah
	                '''
                }
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
