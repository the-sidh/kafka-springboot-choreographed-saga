package br.com.fiap.planeticket.domain.events

import br.com.fiap.planeticket.domain.constants.PLANE_TICKET_FAILURE_TOPIC
import br.com.fiap.planeticket.domain.constants.PLANE_TICKET_SUCCESS_TOPIC
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TopicTest {

    @Test
    fun `given that the outcome was success should get the success topic`(){
        Assertions.assertEquals(PLANE_TICKET_SUCCESS_TOPIC, getTopicFromOutcome(Outcome.SUCCESS))
    }

    @Test
    fun `given that the outcome was failure should get the failure topic`(){
        Assertions.assertEquals(PLANE_TICKET_FAILURE_TOPIC, getTopicFromOutcome(Outcome.FAILURE))
    }
}