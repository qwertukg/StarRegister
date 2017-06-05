package app.controllers.api

import app.mappers.*
import app.models.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class StarController(val mapper: StarMapper, val typeMapper: TypeMapper, val openerMapper: OpenerMapper) {

    @GetMapping("/api/stars")
    @ResponseStatus(HttpStatus.OK)
    fun all(): List<Star> {
        val stars = mapper.index()
        val types = typeMapper.index()
        val openers = openerMapper.index()

        stars.forEach { s ->
            s.type = types.find { it.id == s.typeId } ?: Type()
            s.opener = openers.find { it.id == s.openerId }  ?: Opener()
        }

        return stars
    }

    @GetMapping("/api/stars/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable id: Int) = mapper.read(id)

    @PostMapping("/api/stars")
    @ResponseStatus(HttpStatus.CREATED)
    fun make(@RequestBody model: Star) = mapper.create(model)

    @PutMapping("/api/stars/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun edit(@PathVariable id: Int, @RequestBody model: Star) {
        model.id = id
        return mapper.update(model)
    }

    @DeleteMapping("/api/stars/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun remove(@PathVariable id: Int) {
        val star = mapper.read(id)
        val type = typeMapper.read(star.typeId)
        if (type.removable) mapper.delete(id)
    }
}