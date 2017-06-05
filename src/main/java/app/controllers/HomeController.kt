package app.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class HomeController {
    @GetMapping("/")
    fun auth() = "index"

}
