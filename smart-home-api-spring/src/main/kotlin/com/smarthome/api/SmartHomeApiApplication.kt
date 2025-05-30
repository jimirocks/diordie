package com.smarthome.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmartHomeApiApplication

fun main(args: Array<String>) {
    runApplication<SmartHomeApiApplication>(*args)
}