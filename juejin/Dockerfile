FROM openjdk:17-jdk-slim

WORKDIR /app

COPY niumojuejin.jar /app/niumojuejin.jar

ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/niumo_database
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=123654

EXPOSE 8080

CMD ["java", "-jar", "/app/niumojuejin.jar"]

