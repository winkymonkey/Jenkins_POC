pipeline {
    agent { 
        docker { 
            image 'maven:3.3.3'
        }
    }
    environment {
        DB_ENGINE = 'sqlite'
        MY_SECRET_KEY = credentials('mySecretKey')
    }
    stages {
        stage('Info') {
            steps {
                sh 'echo "Branch Name ${env.BRANCH_NAME}"'
            }
        }
        stage('Build') {
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
        stage('Build-2') {
            parallel {
                stage('leg-1') {
                    steps {
                        sh 'sleep 10'
                        sh 'mvn dependency:tree'
                    }
                }
                stage('leg-2') {
                    steps {
                        sh 'sleep 10'
                        sh 'mvn dependency:tree'
                    }
                }
            }
        }
        stage('Test') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
        stage('Requires Approval') {
            steps {
                input "Does the staging environment look ok?"
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo "Deploying to production"'
                sh 'java -DmySecretKey=$MY_SECRET_KEY -jar target/JenkinsTestRepo-0.0.1-SNAPSHOT.jar'
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
