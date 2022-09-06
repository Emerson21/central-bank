FROM amazoncorretto:11

WORKDIR /app/central-bank

COPY ./target/central-bank-0.0.1-SNAPSHOT.jar ./central-bank.jar

CMD ["java", "-jar", "central-bank.jar"]
