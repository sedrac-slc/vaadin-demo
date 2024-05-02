package com.ei.vaadin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VaadinDemoApplication

fun main(args: Array<String>) {
	runApplication<VaadinDemoApplication>(*args)
}
