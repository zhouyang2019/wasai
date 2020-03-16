#!/bin/bash

echo ------------------ packing ------------------
mvn clean package

echo ------------------ cp jar to lib folder ------------------
cp wasai-rest/target/wasai-rest.war docker-data/wasai/rest/lib

echo ------------------ starting compose docker ------------------
docker-compose up -d
