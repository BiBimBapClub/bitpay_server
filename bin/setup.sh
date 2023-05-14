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
sudo apt-get install redis -y
sudo redis-server

# build jar files
echo "==============BuildJARfile========="
cd ~/bitpay_server
dpkg -S /usr/bin/nohup
chmod +x gradlew
./gradlew build -x check
sudo nohup java -jar /build/libs/bitpay-0.0.0.jar &
