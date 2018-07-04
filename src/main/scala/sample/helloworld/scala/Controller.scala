package sample.helloworld.scala

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import akka.util.Timeout
import com.thing2x.smqd.Smqd
import com.typesafe.config.Config
import spray.json.{JsObject, JsString}

import scala.concurrent.duration._

/**
  * 2018. 7. 4. - Created by Kwon, Yeong Eon
  */
class Controller(name: String, smqd: Smqd, config: Config) extends com.thing2x.smqd.rest.RestController(name, smqd, config) with Directives {

  override val routes: Route = greeting ~ echo

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

  //
  // try "http://localhost:8080/hello" with browser
  //
  def greeting: Route = {
    ignoreTrailingSlash {
      path("hello") {
        complete(StatusCodes.OK, "Hello World!")
      }
    }
  }

  import smqd.Implicit._
  implicit val timeout: Timeout = 3.second

  //
  // try "http://localhost:8080/echo/scala/${any_message}" with browser
  // try "http://localhost:8080/echo/java/${any_message}" with browser
  //
  // This sample shows how rest-api can interact with a service via smqd pub/sub/request api
  //
  def echo: Route = {
    ignoreTrailingSlash {
      path("echo" / "scala" / Remaining.?) { remains =>
        hello("scala", remains)
      } ~
      path("echo" / "java" / Remaining.?) { remains =>
        hello("java", remains)
      }
    }
  }

  private def hello(lang: String, remains: Option[String]): Route = {
    get {
      val jsval = for {
        replyMsg <- smqd.request(s"greeting/$lang", classOf[String], remains.getOrElse("World"))
        replyJson = restSuccess(0, JsObject("name" -> JsString(name), "echo" -> JsString(replyMsg.toString)))
      } yield replyJson

      complete(StatusCodes.OK, jsval)
    }
  }
}