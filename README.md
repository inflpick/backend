# 프로젝트 실행 방법

1. (optional) 도커 이미지 빌드 & 푸시

(프로젝트 루트 경로에서) `docker build -f infra/Dockerfile -t inflpick/api-server . && docker push inflpick/api-server`

2. 도커 실행

(프로젝트 루트 경로에서) `docker compose -p inflpick --env-file infra/.env -f infra/docker-compose-local.yml up -d`