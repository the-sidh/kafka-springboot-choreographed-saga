package br.com.fiap.start

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan()
class StartSaga

fun main(args: Array<String>) {
	runApplication<StartSaga>(*args)
}
