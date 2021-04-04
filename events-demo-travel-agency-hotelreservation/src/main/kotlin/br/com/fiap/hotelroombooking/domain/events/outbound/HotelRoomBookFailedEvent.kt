package br.com.fiap.hotelroombooking.domain.events.outbound

import br.com.fiap.hotelroombooking.domain.entities.Customer
import br.com.fiap.hotelroombooking.domain.events.Event
import io.azam.ulidj.ULID

class HotelRoomBookFailedEvent(
    override val eventId: String,
    val customer: Customer
) : Event(customer) {

    companion object {
        fun create(customer: Customer): HotelRoomBookFailedEvent {
            return HotelRoomBookFailedEvent(
                eventId = ULID.random(),
                customer = customer
            )
        }
    }
}
