package br.com.fiap.hotelroombooking.domain.events

import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_FAILURE_TOPIC
import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_SUCCESS_TOPIC

fun getTopicFromOutcome(outcome: Outcome) = when (outcome) {
    Outcome.SUCCESS -> HOTEL_BOOKING_SUCCESS_TOPIC
    else -> HOTEL_BOOKING_FAILURE_TOPIC
}