package br.com.fiap.hotelroombooking.domain.services

import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_REVERT_SUCCESS_TOPIC
import br.com.fiap.hotelroombooking.domain.entities.customerSampler
import br.com.fiap.hotelroombooking.domain.events.outbound.HotelRoomBookingRevertedEvent
import br.com.fiap.hotelroombooking.resourses.kafka.KafkaEventProducer
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
class HotelBookingRevertServiceTest {

    @Autowired
    private lateinit var service : HotelBookingRevertService

    @MockkBean
    private lateinit var producer: KafkaEventProducer

    @Test
    fun `given a customerId when reverting should emit an event`(){
        val customerId= customerSampler()
        every { producer.send(any(),any()) } just runs
        service.revert(customerId)
        verify { producer.send(any<HotelRoomBookingRevertedEvent>(),
                               HOTEL_BOOKING_REVERT_SUCCESS_TOPIC) }
    }
}
