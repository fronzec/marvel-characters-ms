FROM openjdk:8-jre-slim

RUN mkdir /code
COPY target/*.jar /code

EXPOSE 80
ENTRYPOINT [ "sh", "-c", "java -jar -Dnetworkaddress.cache.ttl=60 -Dnetworkaddress.cache.negative.ttl=10 -Duser.timezone=$TIMEZONE /code/*.jar" ]