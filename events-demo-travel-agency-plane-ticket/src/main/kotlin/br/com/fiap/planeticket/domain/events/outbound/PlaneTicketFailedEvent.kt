package br.com.fiap.planeticket.domain.events.outbound

import br.com.fiap.planeticket.domain.entities.Customer
import br.com.fiap.planeticket.domain.events.Event
import io.azam.ulidj.ULID

class PlaneTicketFailedEvent(
    override val eventId: String,
    val customer: Customer
) : Event(customer) {

    companion object {
        fun create(customer: Customer): PlaneTicketFailedEvent {
            return PlaneTicketFailedEvent(
                eventId = ULID.random(),
                customer = customer
            )
        }
    }
}
