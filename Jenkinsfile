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
                sh 'mvn --version'
                
                sh '''
                    echo "Multiline shell steps works too"
                    ls -lah
                '''
                
                retry(3) {
	                sh 'mvn dependency:tree'
                }
                
                timeout(time: 3, unit: 'MINUTES') {
	                sh 'mvn clean install'
                }
                
                sh 'echo "Database engine is ${DB_ENGINE}"'
                
            }
        }
        stage('test') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
    post {
        always {
            echo 'This will always run'
            mail to: 'pal.abhishek.2008@gail.com',
               subject: "Current Pipeline: ${currentBuild.fullDisplayName}",
               body: "Current build URL ${env.BUILD_URL}"
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
