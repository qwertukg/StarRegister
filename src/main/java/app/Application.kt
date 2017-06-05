package app

import org.springframework.context.annotation.ImportResource
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration

@SpringBootApplication
@ImportResource("classpath:beans.xml")
open class Application : WebMvcAutoConfiguration()

fun main(args: Array<String>) {
    SpringApplication.run(app.Application::class.java, *args)
}
