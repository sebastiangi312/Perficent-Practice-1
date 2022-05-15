FROM maven:3.8.5-openjdk-18-slim as base

WORKDIR /app

COPY .mvn/ .mvn
COPY pom.xml ./
COPY src ./src

FROM base as test
CMD ["mvn", "install"]


FROM base as build
EXPOSE 8081
CMD ["mvn", "spring-boot:run"]
