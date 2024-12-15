# 프로젝트 실행 방법

## IntelliJ IDE로 실행하기

1. 인텔리제이 프로젝트 로딩
2. Ren/Debug Configurations -> VM Option -> -Djasypt.encryptor.password={비밀번호} (암호화 키 비밀번호는 문의 바랍니다.)
3. ActiveProfile=local 로 실행

## 도커로 실행하기

1. (optional) 도커 이미지 빌드 & 푸시

- (프로젝트 루트 경로에서) `docker build -f infra/Dockerfile -t inflpick/api-server . && docker push inflpick/api-server`

2. 도커 실행

- (프로젝트 루트 경로에서) `docker compose -p inflpick --env-file infra/.env -f infra/docker-compose-local.yml up -d`
