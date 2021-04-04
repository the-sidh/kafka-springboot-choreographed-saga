package br.com.fiap.planeticket.domain.services

import br.com.fiap.planeticket.domain.constants.PLANE_TICKET_FAILURE_TOPIC
import br.com.fiap.planeticket.domain.entities.planeTicketSample
import br.com.fiap.planeticket.domain.events.outbound.PlaneTicketFailedEvent
import br.com.fiap.planeticket.domain.gateway.PlaneTicketClient
import br.com.fiap.planeticket.resourses.kafka.KafkaEventProducer
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
class CustomerServiceTest {

    @Autowired
    private lateinit var service: PlaneTicketService

    @MockkBean
    private lateinit var producer: KafkaEventProducer

    @MockkBean
    private lateinit var client: PlaneTicketClient

    @Test
    fun `given that the client responded negatively when executing the action of the service should produce an event that represents the failure of the operation`() {
        every { client.buyPlaneTicket() } returns false
        every { producer.send(any(), any()) } just runs
        service.buyPlaneTicket(planeTicketSample())
        verify {
            producer.send(
                event = any<PlaneTicketFailedEvent>(),
                topic = PLANE_TICKET_FAILURE_TOPIC
            )
        }
    }
}
