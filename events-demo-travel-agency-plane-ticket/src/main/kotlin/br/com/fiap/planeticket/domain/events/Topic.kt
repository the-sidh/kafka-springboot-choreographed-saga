package br.com.fiap.planeticket.domain.events

import br.com.fiap.planeticket.domain.constants.PLANE_TICKET_FAILURE_TOPIC
import br.com.fiap.planeticket.domain.constants.PLANE_TICKET_SUCCESS_TOPIC


fun getTopicFromOutcome(outcome: Outcome) = when (outcome) {
    Outcome.SUCCESS -> PLANE_TICKET_SUCCESS_TOPIC
    else -> PLANE_TICKET_FAILURE_TOPIC
}