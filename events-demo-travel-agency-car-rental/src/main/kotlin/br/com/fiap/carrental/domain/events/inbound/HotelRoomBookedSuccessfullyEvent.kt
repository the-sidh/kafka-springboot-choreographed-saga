package br.com.fiap.carrental.domain.events.inbound

import br.com.fiap.carrental.domain.entities.Customer
import br.com.fiap.carrental.domain.events.Event

class HotelRoomBookedSuccessfullyEvent(
    override val eventId: String,
    val customer: Customer
) : Event(customer)