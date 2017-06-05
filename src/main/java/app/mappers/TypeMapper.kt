package app.mappers

import app.models.Type
import org.apache.ibatis.annotations.*

@Mapper
interface TypeMapper {
    @Select("SELECT * FROM types")
    fun index(): List<Type>

    @Insert("INSERT INTO types(name, removable) VALUES(#{name}, #{removable})")
    fun create(model: Type)

    @Select("SELECT * FROM types WHERE id=#{id}")
    fun read(id: Int): Type

    @Update("UPDATE types SET name=#{name}, removable=#{removable} WHERE id=#{id}")
    fun update(model: Type)

    @Delete("DELETE FROM types WHERE id=#{id}")
    fun delete(id: Int)
}