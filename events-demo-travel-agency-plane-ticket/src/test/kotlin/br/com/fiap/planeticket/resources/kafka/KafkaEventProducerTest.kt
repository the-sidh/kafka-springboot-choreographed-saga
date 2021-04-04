package br.com.fiap.planeticket.resources.kafka

import br.com.fiap.planeticket.domain.constants.PLANE_TICKET_SUCCESS_TOPIC
import br.com.fiap.planeticket.domain.entities.planeTicketSample
import br.com.fiap.planeticket.domain.events.outbound.PlaneTicketSuccessfullyEvent
import br.com.fiap.planeticket.resourses.kafka.KafkaEventProducer
import com.fasterxml.jackson.databind.ObjectMapper

import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.SpykBean
import io.azam.ulidj.ULID
import io.mockk.*
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
class KafkaEventProducerTest {

    @Autowired
    private lateinit var producer: KafkaEventProducer

    @Autowired
    lateinit var realObjectMapper: ObjectMapper

    @MockkBean
    private lateinit var template: KafkaTemplate<String, String>

    @SpykBean
    private lateinit var mapper: ObjectMapper

    @BeforeEach
    private fun forceMockRandom() {
        val freshRandom = ULID.random()
        mockkStatic(ULID::class)
        every { ULID.random() } returns freshRandom
    }

    @AfterEach
    private fun unMockRandom(){
        unmockkStatic(ULID::class)
    }

    @Test
    fun `when sending an event should call the kafkaTemplate send function`() {
        val entity = planeTicketSample()
        val event = PlaneTicketSuccessfullyEvent.create(entity)
        val mockSendResult = mockk<SendResult<String,String>>(relaxed = true)
        every { template.send(any<ProducerRecord<String,String>>()).get() } returns mockSendResult
        producer.send(event, PLANE_TICKET_SUCCESS_TOPIC)
        verify {  template.send(any<ProducerRecord<String,String>>()).get() }
    }
}
