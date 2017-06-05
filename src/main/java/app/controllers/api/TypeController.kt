package app.controllers.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import app.mappers.*
import app.models.*

@RestController
class TypeController(val mapper: TypeMapper) {
    @GetMapping("/api/types")
    @ResponseStatus(HttpStatus.OK)
    fun all() = mapper.index()

    @GetMapping("/api/types/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun get(@PathVariable id: Int) = mapper.read(id)

    @PostMapping("/api/types")
    @ResponseStatus(HttpStatus.CREATED)
    fun make(@RequestBody model: Type) = mapper.create(model)

    @PutMapping("/api/types/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun edit(@PathVariable id: Int, @RequestBody model: Type) {
        model.id = id
        return mapper.update(model)
    }

    @DeleteMapping("/api/types/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun remove(@PathVariable id: Int) = mapper.delete(id)
}