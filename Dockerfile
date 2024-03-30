FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar Wallet.jar
ENTRYPOINT ["java","-jar","/Wallet.jar"]