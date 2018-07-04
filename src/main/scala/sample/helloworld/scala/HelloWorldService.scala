package sample.helloworld.scala

import java.nio.charset.StandardCharsets

import com.thing2x.smqd.{Service, Smqd, TopicPath}
import com.typesafe.config.Config

/**
  * 2018. 7. 4. - Created by Kwon, Yeong Eon
  *
  * Service implementation example for smqd embeded mode in Scala
  */
class HelloWorldService(name: String, smqd: Smqd, config: Config) extends Service(name, smqd, config) {

  private val greeting = config.getString("greeting")

  smqd.subscribe("sensor/+/temp"){ case (topic: TopicPath, msg: Array[Byte]) =>
    val str = new String(msg, StandardCharsets.UTF_8)
    println(s">>> $greeting $str")
  }
}
