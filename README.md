# Quarkus starter template

> If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application 

### Database
* `docker-compose -f src/main/docker/docker-compose.yml up -d`  
    * [PostgreSQL](https://www.postgresql.org/) running on `1111`
    * [pgAdmin](https://www.pgadmin.org/) running on `2222`


You can run your application in dev mode that enables live coding using: `./mvnw compile quarkus:dev`
* Running local on [8080](http://localhost:8080)
* Swagger docs on [docs](http://localhost:8080/docs)
  
---

The application can be packaged using:
`./mvnw package`  
It produces the `template-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.  
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:
```sh
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/template-1.0.0-SNAPSHOT-runner.jar`.


You can create a native executable using: 
`./mvnw package -Pnative`  

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:   
`./mvnw package -Pnative -Dquarkus.native.container-build=true`  

You can then execute your native executable with: `./target/template-1.0.0-SNAPSHOT-runner`
More at  https://quarkus.io/guides/maven-tooling.html.

