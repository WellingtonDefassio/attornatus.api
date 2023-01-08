FROM maven:3-openjdk-17-slim as build
WORKDIR /opt/app
COPY ./ ./
RUN mvn dependency:go-offline 
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim

ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY --from=build /opt/app/target/*.jar /opt/app/*.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]