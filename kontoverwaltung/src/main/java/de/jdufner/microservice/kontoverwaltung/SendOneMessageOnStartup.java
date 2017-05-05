package de.jdufner.microservice.kontoverwaltung;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jdufner on 24.04.17.
 */
@Component
public class SendOneMessageOnStartup implements CommandLineRunner {

  @Value("${kontoverwaltung.mq.queue.name}")
  public String queueName ="defaultQueueName";

  private final RabbitTemplate rabbitTemplate;

  public SendOneMessageOnStartup(final RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  /*
   * Schlechtes Design. Warum wird hier mittels CommandLineRunner eine Nachricht verschicht? Warum nur eine?
   */
  @Override
  public void run(final String... strings) throws Exception {
    System.out.println("Here we're!");
    rabbitTemplate.convertAndSend(queueName, "Hello from RabbitMQ!");
  }

}
