package br.com.fiap.planeticket.domain.events

import br.com.fiap.planeticket.domain.entities.Customer
import br.com.fiap.planeticket.domain.events.outbound.PlaneTicketFailedEvent
import br.com.fiap.planeticket.domain.events.outbound.PlaneTicketSuccessfullyEvent


abstract class Event(val content: Any) {
    abstract val eventId: String

    companion object {
        fun createEventFromOutcome(outcome: Outcome, content: Any): Event =
            when (outcome) {
                Outcome.SUCCESS -> PlaneTicketSuccessfullyEvent.create(
                    entity = content as Customer
                )
                else -> PlaneTicketFailedEvent.create(
                    customer = content as Customer
                )
            }
    }
}

enum class Outcome {
    SUCCESS, FAILURE
}

fun getOutcome(wasSuccess: Boolean): Outcome = when (wasSuccess) {
    true -> Outcome.SUCCESS
    else -> Outcome.FAILURE
}