package com.br.jwtmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JwtManagerApplication

fun main(args: Array<String>) {
	runApplication<JwtManagerApplication>(*args)
}
