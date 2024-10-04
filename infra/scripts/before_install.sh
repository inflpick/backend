#!/bin/bash

docker-compose down
docker image prune -af
docker system prune -af