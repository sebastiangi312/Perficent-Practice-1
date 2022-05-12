FROM maven:3.8.5-openjdk-18-slim

WORKDIR /usr/src/app

COPY . .

EXPOSE 8081

CMD maven spring-boot:run