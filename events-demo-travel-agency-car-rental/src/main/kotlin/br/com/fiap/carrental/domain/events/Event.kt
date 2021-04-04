package br.com.fiap.carrental.domain.events

import br.com.fiap.carrental.domain.entities.Customer
import br.com.fiap.carrental.domain.events.outbound.CarRentFailedEvent
import br.com.fiap.carrental.domain.events.outbound.CarRentedSuccessfullyEvent


abstract class Event(val content: Any) {
    abstract val eventId: String

    companion object {
        fun createEventFromOutcome(outcome: Outcome, content: Any): Event =
            when (outcome) {
                Outcome.SUCCESS -> CarRentedSuccessfullyEvent.create(
                    entity = content as Customer
                )
                else -> CarRentFailedEvent.create(
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