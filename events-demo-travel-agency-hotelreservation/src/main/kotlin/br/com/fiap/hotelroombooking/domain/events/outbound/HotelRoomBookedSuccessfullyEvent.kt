package br.com.fiap.hotelroombooking.domain.events.outbound

import br.com.fiap.hotelroombooking.domain.entities.Customer
import br.com.fiap.hotelroombooking.domain.events.Event
import io.azam.ulidj.ULID

class HotelRoomBookedSuccessfullyEvent(
    override val eventId: String,
    val customer: Customer
) : Event(customer) {

    companion object {
        fun create(customer: Customer): HotelRoomBookedSuccessfullyEvent {
            return HotelRoomBookedSuccessfullyEvent(
                eventId = ULID.random(),
                customer = customer
            )
        }
    }
}
