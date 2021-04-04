package br.com.fiap.start.application.rest.controllers

import br.com.fiap.start.application.rest.dto.SagaStartDTO
import br.com.fiap.start.domain.constants.START_SAGA_TOPIC
import br.com.fiap.start.domain.events.EventProducer
import br.com.fiap.start.domain.events.StartSagaEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StartSagaController(@Autowired @Qualifier("kafkaEventProducer") val eventProducer: EventProducer) {
    @PostMapping("/travel")
    fun execute(@RequestBody dto: SagaStartDTO): ResponseEntity<StartSagaEvent> {
        val event = StartSagaEvent.create( SagaStartDTO.toDomainObjet(dto))
        eventProducer.send(event, START_SAGA_TOPIC)
        return ResponseEntity.status(HttpStatus.CREATED).body(event)
    }
}
