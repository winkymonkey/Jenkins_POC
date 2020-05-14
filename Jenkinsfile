pipeline {
    agent { docker { image 'maven:3.3.3' } }
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
}
