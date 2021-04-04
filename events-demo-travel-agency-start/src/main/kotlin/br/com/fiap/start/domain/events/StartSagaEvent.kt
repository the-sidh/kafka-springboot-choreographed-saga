package br.com.fiap.start.domain.events

import br.com.fiap.start.domain.entities.Customer
import io.azam.ulidj.ULID

class StartSagaEvent(
    override val eventId: String,
    val customer: Customer
) : Event(customer) {

    companion object {
        fun create(customer: Customer): StartSagaEvent {
            return StartSagaEvent(
                eventId = ULID.random(),
                customer = customer
            )
        }
    }
}
