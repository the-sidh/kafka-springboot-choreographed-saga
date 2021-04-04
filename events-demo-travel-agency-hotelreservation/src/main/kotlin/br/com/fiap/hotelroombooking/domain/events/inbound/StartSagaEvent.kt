package br.com.fiap.hotelroombooking.domain.events.inbound

import br.com.fiap.hotelroombooking.domain.entities.Customer
import br.com.fiap.hotelroombooking.domain.events.Event

class StartSagaEvent(
    override val eventId: String,
    val customer: Customer
) : Event(customer)
