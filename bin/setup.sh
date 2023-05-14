#!/bin/bash

# setup.sh
# back-end
# install apt-get update
echo "==============Update APT==========="
sudo apt-get update && apt-get upgrade -y


# install jdk-17
echo "==============InstallJDK17&nohup========="
sudo apt install openjdk-17-jdk nohup -y


# build jar files
echo "==============BuildJARfile========="
cd bitpay-server
chmod +x gradlew
./gradlew build -x check
nohup java -jar complete-v1.0.0.jar & 
