package br.com.fiap.carrental.domain.services

import br.com.fiap.carrental.domain.constants.CAR_RENTAL_REVERTED_TOPIC
import br.com.fiap.carrental.domain.entities.Customer
import br.com.fiap.carrental.domain.events.outbound.CarRentRevertedEvent
import br.com.fiap.carrental.domain.events.EventProducer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CarRentRevertService(@Autowired val eventProducer: EventProducer) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun revert(customer: Customer) {
        logger.info("Car rental reverted for customer ${customer.customerId}")
        eventProducer.send(
            CarRentRevertedEvent.create(customer),
            CAR_RENTAL_REVERTED_TOPIC
        )
    }
}