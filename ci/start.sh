#!/bin/bash
#
#
# Author: Santosh.Kumar <kumarsantosh3295@gmail.com>
# Date: 2019-05-22
#
#     #############################
# Script to start the Spring Boot server for coll-poll assignment
#     ############################

# Switch to the base directory first
cd $( dirname $0 )
cd ..
echo "Current directory is $PWD" >&2

#Update the current packages
apt-get update

# Install project specific requirements
apt-get install mvn

# Uncomment in case of build failure. Creating Clean jar file of project
# mvn clean install

# Run the server
mvn spring-boot:run