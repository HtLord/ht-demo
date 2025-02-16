FROM amazoncorretto:17

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS

WORKDIR /usr/src

ADD target/*-exec.jar app.jar

CMD java -jar $JVM_OPTIONS app.jar

EXPOSE 8080