package br.com.fiap.hotelroombooking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan()
class HotelBooking

fun main(args: Array<String>) {
	runApplication<HotelBooking>(*args)
}
