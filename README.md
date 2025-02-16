Features
---
- AOP
  - Support req/res logging
  - Support coded exception
- OpenAPI v3
  - Support swagger ui at `/swagger-ui.html` 
- i18n
  - Support reply data with variant locale from request 
- Design Pattern
  - Support strategy behavior pattern
  - Support fsm (a.k.a state behavior pattern)
    - Implement with singleton creational pattern
    - Implement with factory method creational pattern
  - Lots of usage of structural pattern with MapStruct 
- Containerize
  - Support docker build
- Cryptology
  - Support BCrypt hash and validation


Preparing
---
- Setup Lombok
    - setup details on https://projectlombok.org/setup/maven

Development Cli
---
- Build Maven
  ```bash
  ./mvnw -Dmaven.test.skip=true package
  ```
- Build docker image
  ```bash
  docker build --no-cache -t ht-demo:latest -f Dockerfile .
  ```
- Run docker image
  ```bash
  docker run -p 8080:8080 -e JVM_OPTIONS='-Dht.service.data.host=https://f648-61-231-134-232.ngrok-free.app' ht-demo:latest
  ```

Implementation Sequence
---
1. Init project with spring initializer
2. Setup h2 database with properties
3. Setup liquibase and database change logs
4. Modify application properties to yaml
5. Add simple entity, repository, and resource
6. Add auditing
7. Add mapstruct for implementing patch in easy way

QA
---
- Can not use IntelliJ - Spring Boot Run Configuration to execute run/debug caused by Lombok or MapStruct not working
  - Make sure Lombok plugin is installed
  - Make sure library has be added in Project Struct(File > Project Struct > Project Setting > Library)
  - Make sure generated folders are properly setup which means 
    - generated-sources has been added to source folders(File > Project Struct > Project Setting > Modules > Target)
    - generated-test-sources has been added to test source folders(File > Project Struct > Project Setting > Modules > Target)
- Can not compile because of annotation processor execute MapStruct before Lombok
  - Make sure every detail of maven has been set. Check [here](https://github.com/mapstruct/mapstruct-examples/blob/main/mapstruct-lombok/pom.xml)
- Breakpoint not working while call rest template to execute resource in IntelliJ
  - Right click on breakpoint and select Thread in Suspend column

Improvement Plan
---
- Split liquibase by context for splitting mock data from database
- Create seperated application to be able on different environment setups like dev, qa, staging, and prod.
- Integrate with tracing system like `Jaeger` for making trace request easier.