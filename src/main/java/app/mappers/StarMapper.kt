package app.mappers

import app.models.Star
import org.apache.ibatis.annotations.*

@Mapper
interface StarMapper {
    @Select("SELECT * FROM stars")
    @Results(
            Result(property = "typeId", column = "type_id"),
            Result(property = "openerId", column = "opener_id")
    )
    fun index(): List<Star>

    @Insert("INSERT INTO stars(name, x, y, type_id, opener_id) " +
            "VALUES(#{name}, #{x}, #{y}, #{typeId}, #{openerId})")
    fun create(model: Star)

    @Select("SELECT * FROM stars " +
            "WHERE id=#{id}")
    @Results(
            Result(property = "typeId", column = "type_id"),
            Result(property = "openerId", column = "opener_id")
    )
    fun read(id: Int): Star

    @Update("UPDATE stars " +
            "SET name=#{name}, x=#{x}, y=#{y}, type_id=#{typeId}, opener_id=#{openerId} " +
            "WHERE id=#{id}")
    fun update(model: Star)

    @Delete("DELETE FROM stars " +
            "WHERE id=#{id}")
    fun delete(id: Int)
}