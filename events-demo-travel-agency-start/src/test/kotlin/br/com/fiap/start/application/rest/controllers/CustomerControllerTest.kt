package br.com.fiap.start.application.rest.controllers

import br.com.fiap.start.application.rest.dto.dtoSampler
import br.com.fiap.start.application.rest.dto.SagaStartDTO
import br.com.fiap.start.domain.constants.START_SAGA_TOPIC
import br.com.fiap.start.domain.events.EventProducer
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
class CustomerControllerTest {

    @MockkBean
    private lateinit var producer: EventProducer

    @Test
    fun `given a valid DTO object when receive a request should return HTTP status 201 `() {
        val controller = StartSagaController(producer)
        every { producer.send(any(),any()) } just Runs
        val dto = dtoSampler()
        val response = controller.execute(dto)
        Assertions.assertEquals(HttpStatus.CREATED, response.statusCode)
        Assertions.assertEquals(SagaStartDTO.toDomainObjet(dto), response.body?.customer)
    }

    @Test
    fun `given a valid DTO object when  receive a request should emit an event`() {
        val controller = StartSagaController(producer)
        every { producer.send(any(),any()) } just Runs
        val dto = dtoSampler()
        controller.execute(dto)
        verify { producer.send(event = any(), START_SAGA_TOPIC) }
    }
}
