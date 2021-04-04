package br.com.fiap.hotelroombooking.domain.events

import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_FAILURE_TOPIC
import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_REVERT_SUCCESS_TOPIC
import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_SUCCESS_TOPIC
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TopicTest {

    @Test
    fun `given that the outcome was success should get the success topic`(){
        Assertions.assertEquals(HOTEL_BOOKING_SUCCESS_TOPIC, getTopicFromOutcome(Outcome.SUCCESS))
    }

    @Test
    fun `given that the outcome was failure should get the failure topic`(){
        Assertions.assertEquals(HOTEL_BOOKING_FAILURE_TOPIC, getTopicFromOutcome(Outcome.FAILURE))
    }
}