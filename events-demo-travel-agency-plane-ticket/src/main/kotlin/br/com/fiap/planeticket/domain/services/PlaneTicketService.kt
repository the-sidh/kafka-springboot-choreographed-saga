package br.com.fiap.planeticket.domain.services

import br.com.fiap.planeticket.domain.entities.Customer
import br.com.fiap.planeticket.domain.events.Event
import br.com.fiap.planeticket.domain.events.EventProducer
import br.com.fiap.planeticket.domain.events.getOutcome
import br.com.fiap.planeticket.domain.events.getTopicFromOutcome
import br.com.fiap.planeticket.domain.gateway.PlaneTicketClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PlaneTicketService(
    @Autowired val client: PlaneTicketClient,
    @Autowired val eventProducer: EventProducer
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    fun buyPlaneTicket(customer: Customer) {
        val result = client.buyPlaneTicket()
        val outcome = getOutcome(result)
        logger.info("When trying to buy a plane ticket for customer ${customer.customerId} received outcome ${outcome.name}")
        eventProducer.send(
            Event.createEventFromOutcome(
                outcome,
                customer
            ),
            getTopicFromOutcome(outcome)
        )
    }

}