echo 'application start...'
docker-compose pull && docker-compose --env-file .env up -d && docker image prune -af