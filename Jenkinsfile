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
            when { anyOf { branch 'master'; branch 'staging' } }
            steps {
                echo "Printing built-in environment variables"
                echo "BRANCH_NAME is ${env.BRANCH_NAME}"
                //
                echo "BUILD_DISPLAY_NAME is ${env.BUILD_DISPLAY_NAME}"
                echo "BUILD_ID is ${env.BUILD_ID}"
                echo "BUILD_NUMBER is ${env.BUILD_NUMBER}"
                echo "BUILD_TAG is ${env.BUILD_TAG}"
                echo "BUILD_URL is ${env.BUILD_URL}"
                //
                echo "CHANGE_AUTHOR is ${env.CHANGE_AUTHOR}"
                echo "CHANGE_AUTHOR_EMAIL is ${env.CHANGE_AUTHOR_EMAIL}"
                echo "CHANGE_BRANCH is ${env.CHANGE_BRANCH}"
                echo "CHANGE_FORK is ${env.CHANGE_FORK}"
                echo "CHANGE_ID is ${env.CHANGE_ID}"
                echo "CHANGE_TARGET is ${env.CHANGE_TARGET}"
                echo "CHANGE_TITLE is ${env.CHANGE_TITLE}"
                echo "CHANGE_URL is ${env.CHANGE_URL}"
                //
                echo "EXECUTOR_NUMBER is ${env.EXECUTOR_NUMBER}"
                //
                echo "JOB_BASE_NAME is ${env.JOB_BASE_NAME}"
                echo "JOB_NAME is ${env.JOB_NAME}"
                echo "JOB_URL is ${env.JOB_URL}"
                //
                echo "TAG_DATE is ${env.TAG_DATE}"
                echo "TAG_NAME is ${env.TAG_NAME}"
                echo "TAG_TIMESTAMP is ${env.TAG_TIMESTAMP}"
                echo "TAG_UNIXTIME is ${env.TAG_UNIXTIME}"
                //
                echo "NODE_NAME is ${env.NODE_NAME}"
                echo "NODE_LABELS is ${env.NODE_LABELS}"
                //
                echo "WORKSPACE is ${env.WORKSPACE}"
                echo "WORKSPACE_TMP is ${env.WORKSPACE_TMP}"
                //
                echo "JENKINS_HOME is ${env.JENKINS_HOME}"
                echo "JENKINS_URL is ${env.JENKINS_URL}"
                //
                echo "GIT_AUTHOR_EMAIL is ${env.GIT_AUTHOR_EMAIL}"
                echo "GIT_AUTHOR_NAME is ${env.GIT_AUTHOR_NAME}"
                echo "GIT_BRANCH is ${env.GIT_BRANCH}"
                echo "GIT_CHECKOUT_DIR is ${env.GIT_CHECKOUT_DIR}"
                echo "GIT_COMMIT is ${env.GIT_COMMIT}"
                echo "GIT_COMMITTER_EMAIL is ${env.GIT_COMMITTER_EMAIL}"
                echo "GIT_COMMITTER_NAME is ${env.GIT_COMMITTER_NAME}"
                echo "GIT_LOCAL_BRANCH is ${env.GIT_LOCAL_BRANCH}"
                echo "GIT_PREVIOUS_COMMIT is ${env.GIT_PREVIOUS_COMMIT}"
                echo "GIT_PREVIOUS_SUCCESSFUL_COMMIT is ${env.GIT_PREVIOUS_SUCCESSFUL_COMMIT}"
                echo "GIT_URL is ${env.GIT_URL}"
                //
                echo "SVN_REVISION is ${env.SVN_REVISION}"
                echo "SVN_URL is ${env.SVN_URL}"
            }
        }
        stage('Build') {
            when { anyOf { branch 'master'; branch 'staging' } }
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
            when { anyOf { branch 'master'; branch 'staging' } }
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
            when { anyOf { branch 'master'; branch 'staging' } }
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
        stage('Requires Approval') {
            when { anyOf { branch 'master'; branch 'staging' } }
            steps {
                input "Does the staging environment look ok?"
            }
        }
        stage('Deploy') {
            when { anyOf { branch 'master'; branch 'staging' } }
            steps {
                sh 'echo "Deploying to production"'
                sh 'java -DmySecretKey=$MY_SECRET_KEY -jar target/Jenkins_POC-0.0.1-SNAPSHOT.jar'
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

