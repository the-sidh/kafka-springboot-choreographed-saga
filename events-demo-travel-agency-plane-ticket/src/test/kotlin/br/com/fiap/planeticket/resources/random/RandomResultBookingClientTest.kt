package br.com.fiap.planeticket.resources.random

import br.com.fiap.planeticket.resourses.random.RandomResultBookingClient
import br.com.fiap.utils.invokePrivateFunction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RandomResultBookingClientTest {
    @Test
    fun `given the number different of 4 when generating the result should return true`() {
        val wasSuccess = invokePrivateFunction<Boolean>(
            callingCLass = RandomResultBookingClient(),
            functionName = "generateResult",
            parameters = arrayOf(1)
        )!!
        Assertions.assertTrue(wasSuccess)
    }


    @Test
    fun `given the number 4 when generating the result should return false`() {
        val wasSuccess = invokePrivateFunction<Boolean>(
            callingCLass = RandomResultBookingClient(),
            functionName = "generateResult",
            parameters = arrayOf(4)
        )!!
        Assertions.assertFalse(wasSuccess)
    }

    @Test
    fun `given a number outside the range of 0 and 4 when generating the result should throw an exception`() {
        assertThrows<Exception> {
            invokePrivateFunction<Boolean>(
                callingCLass = RandomResultBookingClient(),
                functionName = "generateResult",
                parameters = arrayOf(5)
            )!!
        }
    }
}