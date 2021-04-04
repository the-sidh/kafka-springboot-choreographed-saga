package br.com.fiap.start.domain.events

import br.com.fiap.start.domain.entities.Customer


abstract class Event(val content: Any) {
    abstract val eventId: String

    companion object {
        fun createEvent(content: Any): Event =
         StartSagaEvent.create(
             customer = content as Customer
                )
    }
}
