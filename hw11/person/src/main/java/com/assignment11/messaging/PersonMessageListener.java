package com.assignment11.messaging;

import com.assignment11.model.Person;
import com.assignment11.service.PersonService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PersonMessageListener {
  private final PersonService service;
  private final RabbitTemplate tpl;

  public PersonMessageListener(PersonService service, RabbitTemplate tpl) {
    this.service = service;
    this.tpl     = tpl;
  }

  @RabbitListener(queues = RabbitConfig.PERSON_QUEUE)
  public void receive(Person person) {
    try {
      service.save(person);
    } catch (ConstraintViolationException ex) {
      // send error details back to error queue
      tpl.convertAndSend(
        RabbitConfig.PERSON_EXCHANGE,
        RabbitConfig.PERSON_ROUTING_KEY + ".error",
        ex.getConstraintViolations()
          .stream()
          .map(v -> v.getPropertyPath() + ": " + v.getMessage())
          .toList()
      );
    }
  }
}