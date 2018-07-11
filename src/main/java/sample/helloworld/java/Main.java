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

package sample.helloworld.java;

import com.thing2x.smqd.SmqMain;
import com.thing2x.smqd.Smqd;

/**
 * 2018. 6. 28. - Created by Kwon, Yeong Eon
 */
public class Main extends SmqMain {

  @Override
  public String versionString() {
    return "1.2.3";
  }

  @Override
  public String configBasename() {
    return "hello-world";
  }

  private Smqd smqd;

  public Main(String[] args) throws Throwable {
    smqd = super.buildSmqd();
  }

  private void start() {
    if (smqd != null)
      smqd.start();
  }
  private void stop() {
    if (smqd != null)
      smqd.stop();
  }

  public static void main(String[] args) {
    Main m = null;

    try {
      m = new Main(args);
      m.start();

    } catch (Throwable e) {
      e.printStackTrace();
      if (m != null)
        m.stop();
      System.exit(1);
    }
  }
}
