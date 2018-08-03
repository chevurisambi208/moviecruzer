#!/bin/bash

cd Loginservice
source ./env-variable.sh
mvn clean package
docker build -t user-app .
cd ..
cd Backend
source ./env-variable.sh
mvn clean package
docker build -t movie-app .
cd ..
