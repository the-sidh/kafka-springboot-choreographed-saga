package br.com.fiap.carrental.domain.events.outbound

import br.com.fiap.carrental.domain.entities.Customer
import br.com.fiap.carrental.domain.events.Event
import io.azam.ulidj.ULID

class CarRentRevertedEvent(
    override val eventId: String,
    val customer: Customer
) : Event(customer) {
    companion object {
        fun create(customer: Customer): CarRentRevertedEvent {
            return CarRentRevertedEvent(
                eventId = ULID.random(),
                customer = customer
            )
        }
    }
}
