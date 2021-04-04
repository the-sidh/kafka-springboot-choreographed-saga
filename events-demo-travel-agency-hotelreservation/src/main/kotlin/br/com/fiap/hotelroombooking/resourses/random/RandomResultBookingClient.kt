package br.com.fiap.hotelroombooking.resourses.random

import br.com.fiap.hotelroombooking.domain.gateway.HotelBookingClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.*

@Component
@Qualifier("randomResultClient")
class RandomResultBookingClient: HotelBookingClient {

    override fun bookRoom(): Boolean {
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