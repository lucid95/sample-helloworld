package sample.helloworld.java;

import t2x.smqd.SmqMain;
import t2x.smqd.Smqd;

/**
 * 2018. 6. 28. - Created by Kwon, Yeong Eon
 */
public class Main extends SmqMain {
  @Override
  public String logo() {
    return "Hello World in Java";
  }

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
