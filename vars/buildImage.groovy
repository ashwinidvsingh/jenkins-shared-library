#! /user/bin/env groovy
def call(){
    echo "Building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
        sh 'docker build -t ashwinidvsingh/app-image-build-docker:1.1 .'
        sh 'echo $PASSWORD | docker login -u $USERNAME --password-stdin'
        sh 'docker push ashwinidvsingh/app-image-build-docker:1.1'
    }
}