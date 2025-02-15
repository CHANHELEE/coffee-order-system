package org.example.coffeeordersystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoffeeOrderSystemApplication

fun main(args: Array<String>) {
	runApplication<CoffeeOrderSystemApplication>(*args)
}
