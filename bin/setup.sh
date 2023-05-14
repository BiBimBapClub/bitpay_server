#!/bin/bash

# setup.sh
# back-end
# install apt-get update
cd ~
echo "==============Update APT==========="
sudo apt-get update && sudo apt-get upgrade -y


# install jdk-17
echo "==============InstallJDK17&nohup========="
sudo apt install openjdk-17-jdk -y
dpkg -S /usr/bin/nohup

# build jar files
echo "==============BuildJARfile========="
cd ~/bitpay-server
chmod +x gradlew
./gradlew build -x check
nohup java -jar complete-v1.0.0.jar & 
