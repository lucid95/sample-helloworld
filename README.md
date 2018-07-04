# Embeded SMQD Application Sample

[![Build Status](https://travis-ci.org/smqd/sample-helloworld.svg?branch=develop)](https://travis-ci.org/smqd/sample-helloworld)

This sample shows how simple and easy to build your own application using SMQD

1. check out this project
2. simply `sbt run`
3. open your browser with `http://localhost:8080/hello` and `http://localhost:8080/echo/MyMessage`

- [build.sbt](build.sbt)
- [configuration](src/main/resources/hello-world.conf)

### sample codes

#### Scala
- [Main.scala](src/main/scala/sample/helloworld/scala/Main.scala)
- [HelloWorldService.scala](src/main/scala/sample/helloworld/scala/HelloWorldService.scala)

#### Java
- [Main.java](src/main/scala/sample/helloworld/java/Main.java)
- [HelloWorldService.java](src/main/scala/sample/helloworld/java/HelloWorldService.java)


### Run samples

- Scala
`sbt "runMain sample.helloworld.scala.Main"`

- Java
`sbt "runMain sample.helloworld.java.Main"`

