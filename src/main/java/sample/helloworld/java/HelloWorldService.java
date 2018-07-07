package sample.helloworld.java;

import akka.actor.ActorRef;
import com.thing2x.smqd.*;
import com.thing2x.smqd.plugin.Service;
import com.typesafe.config.Config;

import java.nio.charset.StandardCharsets;

/**
 * 2018. 7. 4. - Created by Kwon, Yeong Eon
 *
 * Service implementation example for smqd embeded mode in Java
 */
public class HelloWorldService extends Service implements MessageReceivable {

  private Smqd smqd;
  private String greeting = "Hello";
  private ActorRef subscriber;

  public HelloWorldService(String name, Smqd smqd, Config config) {
    super(name, smqd, config);
    this.smqd = smqd;
    this.greeting = config.getString("greeting");
  }

  @Override
  public void onMessage(TopicPath topicPath, Object message) {
    if (message instanceof ResponsibleMessage) {
      ResponsibleMessage request = (ResponsibleMessage) message;
      smqd.publish(request.getReplyTo(), greeting + request.getMessage().toString());
    }
    else if (message instanceof byte[]) {
      String str = new String((byte[]) message, StandardCharsets.UTF_8);
      System.out.println(">>> " + greeting + str);
    }
  }

  @Override
  public void start() {
    subscriber = this.smqd.subscribe(FilterPath.apply("greeting/java/#"), this);
  }

  @Override
  public void stop() {
    this.smqd.unsubscribe(FilterPath.apply("greeting/java/#"), subscriber);
  }
}
