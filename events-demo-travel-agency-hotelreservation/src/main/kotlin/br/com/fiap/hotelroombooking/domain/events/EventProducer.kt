package br.com.fiap.hotelroombooking.domain.events

interface EventProducer {
    fun send(event: Event, topic: String)
}