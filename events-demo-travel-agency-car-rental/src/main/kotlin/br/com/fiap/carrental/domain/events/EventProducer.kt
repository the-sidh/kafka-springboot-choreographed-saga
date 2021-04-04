package br.com.fiap.carrental.domain.events

interface EventProducer {
    fun send(event: Event, topic: String)
}