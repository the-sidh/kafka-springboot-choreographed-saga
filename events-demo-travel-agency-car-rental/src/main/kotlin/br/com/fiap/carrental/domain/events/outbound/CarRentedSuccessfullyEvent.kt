package br.com.fiap.carrental.domain.events.outbound

import br.com.fiap.carrental.domain.entities.Customer
import br.com.fiap.carrental.domain.events.Event
import io.azam.ulidj.ULID

class CarRentedSuccessfullyEvent(
    override val eventId: String,
    val customer: Customer
) : Event(customer) {

    companion object {
        fun create(entity: Customer): CarRentedSuccessfullyEvent {
            return CarRentedSuccessfullyEvent(
                eventId = ULID.random(),
                customer = entity
            )
        }
    }
}
