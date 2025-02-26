FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY bin/ /app/bin/

COPY resources/ /app/resources/

ENTRYPOINT ["java", "-XX:+ShowCodeDetailsInExceptionMessages", "-cp", "/app/bin", "mainGameFlipAndFind.TestGame"]