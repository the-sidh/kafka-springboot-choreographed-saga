package br.com.fiap.carrental.domain.services

import br.com.fiap.carrental.domain.constants.CAR_RENTAL_REVERTED_TOPIC
import br.com.fiap.carrental.domain.entities.cutomerSample
import br.com.fiap.carrental.domain.events.outbound.CarRentRevertedEvent
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
class CarRentalRevertServiceTest {

    @Autowired
    private lateinit var service: CarRentRevertService

    @MockkBean
    private lateinit var producer: KafkaEventProducer

    @Test
    fun `given a customerId when reverting should emit an event`() {
        val customer = cutomerSample()
        every { producer.send(any(), any()) } just runs
        service.revert(customer)
        verify {
            producer.send(
                any<CarRentRevertedEvent>(),
                CAR_RENTAL_REVERTED_TOPIC
            )
        }
    }
}
