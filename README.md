Preparing
---
- Setup Lombok
    - setup details on https://projectlombok.org/setup/maven

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

Design Pattern
---
- Factory
  - Direct usage by Lombok