#! /user/bin/env groovy
def call(string imageName){
    echo "Building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
        sh "docker build -t $imageName ."
        sh 'echo $PASSWORD | docker login -u $USERNAME --password-stdin'
        sh "docker push $imageName"
    }
}