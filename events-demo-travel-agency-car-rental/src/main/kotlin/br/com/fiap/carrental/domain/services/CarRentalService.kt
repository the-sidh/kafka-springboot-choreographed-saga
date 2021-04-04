package br.com.fiap.carrental.domain.services

import br.com.fiap.carrental.domain.entities.Customer
import br.com.fiap.carrental.domain.events.Event
import br.com.fiap.carrental.domain.events.EventProducer
import br.com.fiap.carrental.domain.events.getOutcome
import br.com.fiap.carrental.domain.events.getTopicFromOutcome
import br.com.fiap.carrental.domain.gateway.CarRentClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CarRentalService(
    @Autowired val client: CarRentClient,
    @Autowired val eventProducer: EventProducer
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    fun rentCar(customer: Customer) {
        val result = client.rentCar()
        val outcome = getOutcome(result)
        logger.info("When trying to rent a car for customer ${customer.customerId} received outcome ${outcome.name}")
        eventProducer.send(
            Event.createEventFromOutcome(
                outcome,
                customer
            ),
            getTopicFromOutcome(outcome)
        )
    }

}