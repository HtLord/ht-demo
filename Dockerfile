FROM amazoncorretto:17

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS

WORKDIR /usr/src

ADD target/*-exec.jar app.jar

CMD echo $JVM_OPTIONS
CMD java -jar -Dht.service.data.host=https://f648-61-231-134-232.ngrok-free.app app.jar

EXPOSE 8080