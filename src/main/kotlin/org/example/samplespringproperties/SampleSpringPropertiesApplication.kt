package org.example.samplespringproperties

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.core.env.Environment

@SpringBootApplication
@PropertySources(
    PropertySource("classpath:user.properties"),
    PropertySource("classpath:landmark.properties")
)
class SampleSpringPropertiesApplication(
    @Autowired private val environment: Environment,
) : CommandLineRunner {

    @Value("\${france}")
    private val landmarkFrance: String? = null

    @Value("\${italy:Pizza Tower}")
    private val landmarkItaly: String? = null

    override fun run(vararg args: String?) {
        println("One dollar = ${environment.getProperty("yen")} Yen")
        println("One dollar = ${environment.getProperty("EGP")} EGP")
        println("HOME = ${environment.getProperty("HOME")}")
        println("name = ${environment.getProperty("name", "Anon")}")
        println("The most famous landmark in Egypt is ${environment.getProperty("egypt")}")

        val landmarks = environment.getProperty("egypt")!!.split(",").map { it.trim() }

        println("One of the most famous landmark in Egypt is ${landmarks.random()}")

        println(landmarkFrance)
        println(landmarkItaly)
    }
}

fun main(args: Array<String>) {
    runApplication<SampleSpringPropertiesApplication>(*args)
}
