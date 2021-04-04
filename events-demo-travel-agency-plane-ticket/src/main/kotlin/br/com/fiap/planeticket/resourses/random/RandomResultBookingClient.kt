package br.com.fiap.planeticket.resourses.random

import br.com.fiap.planeticket.domain.gateway.PlaneTicketClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.*

@Component
@Qualifier("randomResultClient")
class RandomResultBookingClient: PlaneTicketClient {

    override fun buyPlaneTicket(): Boolean {
        return generateResult(rand(0,4))
    }

    private fun generateResult(number: Int): Boolean {
        require(number in 0..4)
        return number != 4
    }
    private fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }
}