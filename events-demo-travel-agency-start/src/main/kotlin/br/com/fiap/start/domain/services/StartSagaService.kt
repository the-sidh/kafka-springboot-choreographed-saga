package br.com.fiap.start.domain.services


import br.com.fiap.start.domain.constants.START_SAGA_TOPIC
import br.com.fiap.start.domain.entities.Customer
import br.com.fiap.start.domain.events.EventProducer
import br.com.fiap.start.domain.events.StartSagaEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class StartSagaService(
    @Autowired val eventProducer: EventProducer
) {
    fun startSaga(customer: Customer) {
        eventProducer.send(
            StartSagaEvent.create(customer),
            START_SAGA_TOPIC
        )
    }
}
