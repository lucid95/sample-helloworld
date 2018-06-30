// Copyright 2018 UANGEL
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package sample.helloworld.scala

import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import com.typesafe.config.Config
import spray.json._
import com.thing2x.smqd.Smqd

/**
  * 2018. 6. 27. - Created by Kwon, Yeong Eon
  */
object Main extends com.thing2x.smqd.SmqMain {

  override val logo: String =
    """
      | _   _      _ _        __        __         _     _
      || | | | ___| | | ___   \ \      / /__  _ __| | __| |
      || |_| |/ _ \ | |/ _ \   \ \ /\ / / _ \| '__| |/ _` |
      ||  _  |  __/ | | (_) |   \ V  V / (_) | |  | | (_| |
      ||_| |_|\___|_|_|\___/     \_/\_/ \___/|_|  |_|\__,_| """.stripMargin

  override val versionString: String = "1.2.3"

  override val configBasename: String = "hello-world"

  try{
    val smqd = super.buildSmqd()
    smqd.start()
  }
  catch {
    case ex: Throwable =>
      println("starting failed", ex)
      System.exit(1)
  }
}

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