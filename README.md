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

QA
---
- Resource return empty object even if database already has some data in develop mode?
    - Major problem MAYBE your getter/setter is missing.
    - Because lombok generate code not be triggered by `java ...`.
    - Make IDE using `mvnw spring-boot:run`.
    - Or other ways to force annotation process been run.
    - Checking classes is looks like your expectation, or not.