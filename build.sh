#!/bin/bash

cd Loginservice
source ./env-variable.sh
mvn clean package
cd ..
cd Backend
source ./env-variable.sh
mvn clean package
cd ..
