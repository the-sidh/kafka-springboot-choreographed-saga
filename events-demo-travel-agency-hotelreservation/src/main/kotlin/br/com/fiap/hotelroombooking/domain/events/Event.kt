package br.com.fiap.hotelroombooking.domain.events

import br.com.fiap.hotelroombooking.domain.entities.Customer
import br.com.fiap.hotelroombooking.domain.events.Outcome.*
import br.com.fiap.hotelroombooking.domain.events.outbound.HotelRoomBookFailedEvent
import br.com.fiap.hotelroombooking.domain.events.outbound.HotelRoomBookedSuccessfullyEvent

abstract class Event(val content: Any) {
    abstract val eventId: String

    companion object {
        fun createEventFromOutcome(outcome: Outcome, content: Any): Event =
            when (outcome) {
                SUCCESS -> HotelRoomBookedSuccessfullyEvent.create(
                    customer = content as Customer
                )
                else -> HotelRoomBookFailedEvent.create(
                    customer = content as Customer
                )
            }
    }
}

enum class Outcome {
    SUCCESS, FAILURE
}

fun getOutcome(wasSuccess: Boolean): Outcome = when (wasSuccess) {
    true -> SUCCESS
    else -> FAILURE
}