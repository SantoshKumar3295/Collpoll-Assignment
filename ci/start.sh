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

# Install project specific requirements
apt-get install mvn

# Creating jar file of project
mvn clean install

# Run the jar file to start the server
java -jar target/todoList-0.0.1-SNAPSHOT.jar