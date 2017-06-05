package app.controllers.api

import app.mappers.OpenerMapper
import app.models.Opener
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class OpenerController(val mapper: OpenerMapper) {
    @GetMapping("/api/openers")
    @ResponseStatus(HttpStatus.OK)
    fun all() = mapper.index()

    @GetMapping("/api/openers/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun get(@PathVariable id: Int) = mapper.read(id)

    @PostMapping("/api/openers")
    @ResponseStatus(HttpStatus.CREATED)
    fun make(@RequestBody model: Opener) = mapper.create(model)

    @PutMapping("/api/openers/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun edit(@PathVariable id: Int, @RequestBody model: Opener) {
        model.id = id
        return mapper.update(model)
    }

    @DeleteMapping("/api/openers/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun remove(@PathVariable id: Int) = mapper.delete(id)
}