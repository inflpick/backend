#!/bin/bash

# Load variables from .env file
if [ -f /root/app/.env ]; then
  source /root/app/.env
fi

# Docker login using environment variables from .env
docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_TOKEN
docker-compose pull
docker-compose --env-file /root/app/.env up -d