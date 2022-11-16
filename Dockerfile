FROM eclipse-temurin

WORKDIR /app

RUN apt-get update && \
    apt-get install dos2unix && \
    apt-get clean
    
COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./
RUN ["dos2unix", "mvnw"]
RUN [ "./mvnw", "dependency:go-offline" ]
COPY src ./src


CMD ["./mvnw", "spring-boot:run"]
