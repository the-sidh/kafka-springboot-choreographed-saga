package br.com.fiap.hotelroombooking.resourses.kafka

import br.com.fiap.hotelroombooking.domain.events.Event
import br.com.fiap.hotelroombooking.domain.events.outbound.HotelRoomBookedSuccessfullyEvent
import br.com.fiap.hotelroombooking.domain.events.EventProducer
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.Header
import org.apache.kafka.common.header.internals.RecordHeader
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaEventProducer(val objectMapper: ObjectMapper, val template: KafkaTemplate<String, String>) :
    EventProducer {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun send(event: Event, topic: String) {
        val producerRecord = getProducerRecord(event,topic)
        logger.info(
            "sending event ${
                String(
                    producerRecord.headers().first().value()
                )
            } to $topic"
        )
        template.send(producerRecord).get()
    }

    private fun getProducerRecord(event: Event, topic: String): ProducerRecord<String, String> {
        val serializedEvent = objectMapper.writeValueAsString(event)
        val eventTypeHeader = RecordHeader("eventType", objectMapper.writeValueAsBytes(
            event::class.simpleName!!))
        val headers = listOf<Header>(eventTypeHeader)
        return ProducerRecord(
            topic,
            null,
            event.eventId,
            serializedEvent,
            headers)
    }
}