# Tenant service
The tenant service is reponsible for handling organization and tenant data

## Ownership
This project is currently owned by [Team Modak](mailto:TeamModak@jda.com).

## Build
 Run `./gradlew clean check`

## Running Locally 
 To launch the application locally with the profile 'no-dependencies', simply run `./gradlew clean bootRun`
 
 Run in remote debugging mode `./gradlew clean bootRun --debug-jvm --stacktrace`

 Currently this service needs a local PostgreSQL installation to run, details of which are given in 
 `application-local.yaml`

 
## Running in docker

 Run `./gradlew jibBuildTar`
 
 Run `docker load --input build/jib-image.tar`
 
 Run `docker run -p 8080:8080 waterfox83/kubernetes-starter`
 

## Deploying to Azure Kubernetes Cluster

For running on AKS, we should create and push the image to ACR. We need to create the image
first though, run `./gradlew jibBuildTar` and `docker load --input build/jib-image.tar` to create and load the image. 
 
Push it to ACR: `docker push waterfox83/kubernetes-starter:latest`

Deploy the image on Kubernetes: `kubectl apply -f deploy/starter-httpdeploy.yaml`