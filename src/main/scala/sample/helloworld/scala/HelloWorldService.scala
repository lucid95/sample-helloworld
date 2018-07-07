package sample.helloworld.scala

import java.nio.charset.StandardCharsets

import akka.actor.ActorRef
import com.thing2x.smqd.plugin.Service
import com.thing2x.smqd.{ResponsibleMessage, Smqd, TopicPath}
import com.typesafe.config.Config

/**
  * 2018. 7. 4. - Created by Kwon, Yeong Eon
  *
  * Service implementation example for smqd embeded mode in Scala
  */
class HelloWorldService(name: String, smqd: Smqd, config: Config) extends Service(name, smqd, config) {

  private var _subscriber: ActorRef = _

  override def start(): Unit = {
    val greeting = config.getString("greeting")

    _subscriber = smqd.subscribe("greeting/scala/#"){
      case (topic: TopicPath, ResponsibleMessage(replyTo, pigyback)) =>
        smqd.publish(replyTo, s"$greeting ${pigyback.toString}")

      case (topic: TopicPath, msg: Array[Byte]) =>
        val str = new String(msg, StandardCharsets.UTF_8)
        println(s">>> $greeting $str")
    }
  }

  override def stop(): Unit = {
    smqd.unsubscribe("greeting/scala/#", _subscriber)
  }
}
