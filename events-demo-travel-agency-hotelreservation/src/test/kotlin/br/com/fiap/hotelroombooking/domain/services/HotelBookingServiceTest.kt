package br.com.fiap.hotelroombooking.domain.services

import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_FAILURE_TOPIC
import br.com.fiap.hotelroombooking.domain.entities.customerSampler
import br.com.fiap.hotelroombooking.domain.events.outbound.HotelRoomBookFailedEvent
import br.com.fiap.hotelroombooking.domain.gateway.HotelBookingClient
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
class HotelBookingServiceTest {

    @Autowired
    private lateinit var service: HotelBookingService

    @MockkBean
    private lateinit var producer: KafkaEventProducer

    @MockkBean
    private lateinit var client: HotelBookingClient

    @Test
    fun `given that the client responded negatively when executing the action of the service should produce an event that represents the failure of the operation`() {
        every { client.bookRoom() } returns false
        every { producer.send(any(), any()) } just runs
        service.bookRoom(customerSampler())
        verify {
            producer.send(
                event = any<HotelRoomBookFailedEvent>(),
                topic = HOTEL_BOOKING_FAILURE_TOPIC
            )
        }
    }
}
