#!/bin/bash

# Load variables from .env file
APP_PATH=/root/app
echo pwd >> /root/app/pwd.txt
cd ${APP_PATH} || exit
if [ -f ${APP_PATH}/.env ]; then
  source ${APP_PATH}/.env
fi

# Docker login using environment variables from .env
docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_TOKEN
docker-compose pull
docker-compose --env-file ${APP_PATH}/.env up -d