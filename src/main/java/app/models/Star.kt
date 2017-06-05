package app.models

class Star(
        var id: Int = 0,
        var name: String = "",
        var x: String = "",
        var y: String = "",
        var typeId: Int = 0,
        var type: Type? = null,
        var openerId: Int = 0,
        var opener: Opener? = null
)