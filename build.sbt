name := "sample-helloworld"

version := "0.1"

scalaVersion := "2.12.6"

resolvers += Resolver.bintrayRepo("smqd", "smqd")

libraryDependencies += "t2x.smqd" %% "smqd-core" % "0.1.0"

mainClass := Some("sample.helloworld.Main")
