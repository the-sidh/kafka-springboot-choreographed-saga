package br.com.fiap.start.domain.services

import br.com.fiap.start.domain.constants.START_SAGA_TOPIC
import br.com.fiap.start.domain.entities.Customer
import br.com.fiap.start.domain.events.EventProducer
import br.com.fiap.start.domain.events.StartSagaEvent
import com.ninjasquad.springmockk.MockkBean
import io.azam.ulidj.ULID
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
class StartSagaServiceTest {

    @Autowired
    private lateinit var service: StartSagaService

    @MockkBean
    private lateinit var producer: EventProducer

    @Test
    fun `given a customerId when reverting should emit an event`() {
        val customerId = ULID.random()
        every { producer.send(any(), any()) } just runs
        service.startSaga(Customer(customerId))
        verify {
            producer.send(
                any<StartSagaEvent>(),
                START_SAGA_TOPIC
            )
        }
    }
}
