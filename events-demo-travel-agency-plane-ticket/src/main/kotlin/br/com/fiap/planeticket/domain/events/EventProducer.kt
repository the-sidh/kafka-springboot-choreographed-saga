package br.com.fiap.planeticket.domain.events

interface EventProducer {
    fun send(event: Event, topic: String)
}