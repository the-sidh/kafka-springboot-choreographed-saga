package br.com.fiap.start.domain.events

interface EventProducer {
    fun send(event: Event, topic: String)
}