package app.mappers

import app.models.Opener
import org.apache.ibatis.annotations.*

@Mapper
interface OpenerMapper {
    @Select("SELECT * FROM openers")
    fun index(): List<Opener>

    @Insert("INSERT INTO openers(name) VALUES(#{name})")
    fun create(model: Opener)

    @Select("SELECT * FROM openers WHERE id=#{id}")
    fun read(id: Int): Opener

    @Update("UPDATE openers SET name=#{name} WHERE id=#{id}")
    fun update(model: Opener)

    @Delete("DELETE FROM openers WHERE id=#{id}")
    fun delete(id: Int)
}

