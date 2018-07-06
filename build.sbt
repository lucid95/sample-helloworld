name := "sample-helloworld"

version := "0.1"

scalaVersion := "2.12.6"

resolvers += Resolver.sonatypeRepo("public")

libraryDependencies += "com.thing2x" %% "smqd-core" % "0.3.4"

mainClass := Some("sample.helloworld.scala.Main")
