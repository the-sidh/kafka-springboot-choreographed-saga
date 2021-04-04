package br.com.fiap.hotelroombooking

import br.com.fiap.hotelroombooking.application.rest.dto.HotelRoomBookingDTO
import br.com.fiap.hotelroombooking.application.rest.dto.dtoSampler
import br.com.fiap.hotelroombooking.domain.constants.HOTEL_BOOKING_SUCCESS_TOPIC
import br.com.fiap.hotelroombooking.domain.events.outbound.HotelRoomBookedSuccessfullyEvent
import com.fasterxml.jackson.databind.ObjectMapper
import io.azam.ulidj.ULID
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.awaitility.kotlin.await
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.Duration

const val PATH = "/hotel/book"

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@EmbeddedKafka(topics = [HOTEL_BOOKING_SUCCESS_TOPIC])
@TestPropertySource(
    properties = [
        "spring.kafka.producer.bootstrap-servers=\${spring.embedded.kafka.brokers}",
        "spring.kafka.admin.properties.bootstrap-servers=\${spring.embedded.kafka.brokers}"])
class EventProducingTests() {
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var embeddedKafkaBroker: EmbeddedKafkaBroker

    @Autowired
    private lateinit var restTestTemplate: TestRestTemplate

    private lateinit var consumer: Consumer<String, String>

    @BeforeEach
    fun setup() {
        val configs = KafkaTestUtils.consumerProps("group1", "true", embeddedKafkaBroker)
        consumer = DefaultKafkaConsumerFactory(configs, StringDeserializer(), StringDeserializer()).createConsumer()
        embeddedKafkaBroker.consumeFromAllEmbeddedTopics(consumer)
        val freshRandom = ULID.random()
        mockkStatic(ULID::class)
        every { ULID.random() } returns freshRandom
    }

    @AfterEach
    fun shutDown() {
        consumer.close()
        unmockkStatic(ULID::class)
    }

    @Test
    fun `given a valid DTO when call POST should return HTTP Status 201 and produce the correct event`() {
        val dto = dtoSampler()
        val request = HttpEntity<HotelRoomBookingDTO>(dto)
        val headers = HttpHeaders()
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
        val response = restTestTemplate.exchange(PATH, HttpMethod.POST, request, HotelRoomBookingDTO::class.java)
        Assertions.assertEquals(HttpStatus.CREATED, response.statusCode)
        val consumerRecord = KafkaTestUtils.getSingleRecord(consumer, HOTEL_BOOKING_SUCCESS_TOPIC)
            .value()
        val expectEventContent = getExpectedEventFromDTO(dto)

        await.atMost(Duration.ofSeconds(5L))
            .untilAsserted {
                Assertions.assertEquals(expectEventContent, consumerRecord)
            }

    }

    private fun getExpectedEventFromDTO(dto: HotelRoomBookingDTO) =
            objectMapper.writeValueAsString(
                HotelRoomBookedSuccessfullyEvent.create(
                    HotelRoomBookingDTO.toDomainObjet(dto)))
}
