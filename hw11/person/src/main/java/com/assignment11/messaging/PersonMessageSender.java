package com.assignment11.messaging;

import com.assignment11.model.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PersonMessageSender {
  private final RabbitTemplate tpl;

  public PersonMessageSender(RabbitTemplate tpl) {
    this.tpl = tpl;
  }

  public void send(Person p) {
    tpl.convertAndSend(
      RabbitConfig.PERSON_EXCHANGE,
      RabbitConfig.PERSON_ROUTING_KEY,
      p
    );
  }
}
