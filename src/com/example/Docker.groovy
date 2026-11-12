#! /user/bin/env groovy
package com.example
    class Docker{
        def script

        Docker(script){
            this.script = script
        }

        def buildDockerImage(String imageName){
            script.echo "Build the Docker image..."
            script.withCredentials([script.usernamePassword(credentialsId:'dockerhub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]){
                script.sh "docker build -t $imageName ."
                script.sh 'echo $PASS | docker login -u $USER --password-stdin'
                script.sh "docker push $imageName"
            }
        }
    }