package br.com.fiap.planeticket.domain.events.outbound

import br.com.fiap.planeticket.domain.entities.Customer
import br.com.fiap.planeticket.domain.events.Event
import io.azam.ulidj.ULID

class PlaneTicketSuccessfullyEvent(
    override val eventId: String,
    val customer: Customer
) : Event(customer) {

    companion object {
        fun create(entity: Customer): PlaneTicketSuccessfullyEvent {
            return PlaneTicketSuccessfullyEvent(
                eventId = ULID.random(),
                customer = entity
            )
        }
    }
}
