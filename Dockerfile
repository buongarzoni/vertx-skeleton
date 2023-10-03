FROM gradle:8.1.1-jdk17-focal AS builder

WORKDIR /build

COPY settings.gradle.kts gradle.properties /build/

RUN gradle clean build --no-daemon > /dev/null 2>&1 || true

COPY ./ ./
RUN gradle shadowjar

FROM openjdk:19 AS runner
COPY --from=builder /build/app/build/libs/*.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/app.jar"]