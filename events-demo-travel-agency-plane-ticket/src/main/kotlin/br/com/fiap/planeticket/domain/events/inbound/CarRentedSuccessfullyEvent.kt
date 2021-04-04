package br.com.fiap.planeticket.domain.events.inbound

import br.com.fiap.planeticket.domain.entities.Customer
import br.com.fiap.planeticket.domain.events.Event

class CarRentedSuccessfullyEvent(
    override val eventId: String,
    val customer: Customer
) : Event(customer)