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

/**
  * 2018. 6. 27. - Created by Kwon, Yeong Eon
  *
  * Scala Main for an example of smqd embeded mode
  */
object Main extends App with com.thing2x.smqd.SmqMainBase {

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
