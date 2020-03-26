#!/usr/bin/env bash

echo " ++++++++++++ Starting Gradle build  +++++++++++++ "

gradle clean build

echo " ++++++++++++ Building Tar  ++++++++++++ "

gradle jibBuildTar

echo " ++++++++++++ Removing previous docker image  ++++++++++++ "

docker rmi waterfox83/kubernetes-starter:latest

echo " ++++++++++++ Loading new docker image  ++++++++++++ "

docker load --input build/jib-image.tar

echo " ++++++++++++ Logging into ACR  ++++++++++++ "

docker login -u <docker username> -p <docker password>

echo " ++++++++++++ Pushing new docker image to ACR  ++++++++++++ "

docker push waterfox83/kubernetes-starter:latest

echo " ++++++++++++ Deleting old kubectl deployments  ++++++++++++ "

kubectl delete deployment kubernetes-starter-deployment

echo " ++++++++++++ Creating new kubectl deployments  ++++++++++++ "

kubectl apply -f deploy/starter-httpdeploy.yaml
