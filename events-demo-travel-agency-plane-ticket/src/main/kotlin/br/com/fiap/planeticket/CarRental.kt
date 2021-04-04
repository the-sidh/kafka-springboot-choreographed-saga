package br.com.fiap.planeticket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan()
class CarRental

fun main(args: Array<String>) {
	runApplication<CarRental>(*args)
}
