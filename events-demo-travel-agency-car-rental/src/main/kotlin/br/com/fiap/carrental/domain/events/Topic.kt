package br.com.fiap.carrental.domain.events

import br.com.fiap.carrental.domain.constants.CAR_RENTAL_FAILURE_TOPIC
import br.com.fiap.carrental.domain.constants.CAR_RENTAL_SUCCESS_TOPIC


fun getTopicFromOutcome(outcome: Outcome) = when (outcome) {
    Outcome.SUCCESS -> CAR_RENTAL_SUCCESS_TOPIC
    else -> CAR_RENTAL_FAILURE_TOPIC
}