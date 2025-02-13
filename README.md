Preparing
---
- Setup Lombok
    - setup details on https://projectlombok.org/setup/maven

QA
---
- Resource return empty object even if database already has some data in develop mode?
    - Major problem MAYBE your getter/setter is missing.
    - Because lombok generate code not be triggered by `java ...`.
    - Make IDE using `mvnw spring-boot:run`.
    - Or other ways to force annotation process been run.
    - Checking classes is looks like your expectation, or not.