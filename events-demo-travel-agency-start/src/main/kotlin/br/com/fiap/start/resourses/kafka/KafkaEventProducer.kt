package br.com.fiap.start.resourses.kafka

import br.com.fiap.start.domain.events.Event
import br.com.fiap.start.domain.events.EventProducer
import br.com.fiap.start.domain.events.StartSagaEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.Header
import org.apache.kafka.common.header.internals.RecordHeader
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaEventProducer(val objectMapper: ObjectMapper, val template: KafkaTemplate<String, String>) :
    EventProducer {

    override fun send(event: Event, topic: String) {
        val producerRecord = getProducerRecord(event,topic)
        template.send(producerRecord).get()
    }

    private fun getProducerRecord(event: Event, topic: String): ProducerRecord<String, String> {
        val serializedEvent = objectMapper.writeValueAsString(event)
        val eventTypeHeader = RecordHeader("eventType", objectMapper.writeValueAsBytes(
            StartSagaEvent::class.simpleName!!))
        val headers = listOf<Header>(eventTypeHeader)
        return ProducerRecord(
            topic,
            null,
            event.eventId,
            serializedEvent,
            headers)
    }
}