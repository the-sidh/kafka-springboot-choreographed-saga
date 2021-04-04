package br.com.fiap.carrental.domain.services

import br.com.fiap.carrental.domain.constants.CAR_RENTAL_FAILURE_TOPIC
import br.com.fiap.carrental.domain.entities.cutomerSample
import br.com.fiap.carrental.domain.events.outbound.CarRentFailedEvent
import br.com.fiap.carrental.domain.gateway.CarRentClient
import br.com.fiap.carrental.resourses.kafka.KafkaEventProducer
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
class CarRentalServiceTest {

    @Autowired
    private lateinit var service: CarRentalService

    @MockkBean
    private lateinit var producer: KafkaEventProducer

    @MockkBean
    private lateinit var client: CarRentClient

    @Test
    fun `given that the client responded negatively when executing the action of the service should produce an event that represents the failure of the operation`() {
        every { client.rentCar() } returns false
        every { producer.send(any(), any()) } just runs
        service.rentCar(cutomerSample())
        verify {
            producer.send(
                event = any<CarRentFailedEvent>(),
                topic = CAR_RENTAL_FAILURE_TOPIC
            )
        }
    }
}
