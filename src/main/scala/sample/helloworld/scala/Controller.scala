package sample.helloworld.scala

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import com.thing2x.smqd.Smqd
import com.typesafe.config.Config
import spray.json.{JsObject, JsString}

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

  //
  // try "http://localhost:8080/echo/${any_message}" with browser
  //
  def echo: Route = {
    ignoreTrailingSlash {
      path("echo" / Remaining.?) { tails =>
        get {
          complete(StatusCodes.OK, restSuccess(0, JsObject("name"->JsString(name), "echo" -> JsString(tails.getOrElse("")))))
        }
      }
    }
  }
}