# 1. Gradle 빌드 이미지 설정
FROM gradle:8.10.1-jdk21 AS builder

# 2. 작업 디렉토리 생성
WORKDIR /home/gradle/project

# 3. 소스 코드 복사
COPY . .

# 4. 의존성 캐싱을 위한 Gradle 빌드 초기화
RUN gradle build -x test --no-daemon

# 5. 런타임 이미지 설정
FROM amazoncorretto:21-alpine

# 6. 빌드된 JAR 파일 복사
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# 7. 포트 노출
EXPOSE 8080

# 8. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]

