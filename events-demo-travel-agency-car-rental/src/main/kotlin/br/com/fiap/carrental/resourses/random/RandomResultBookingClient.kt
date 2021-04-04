package br.com.fiap.carrental.resourses.random

import br.com.fiap.carrental.domain.gateway.CarRentClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.*

@Component
@Qualifier("randomResultClient")
class RandomResultBookingClient: CarRentClient {

    override fun rentCar(): Boolean {
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