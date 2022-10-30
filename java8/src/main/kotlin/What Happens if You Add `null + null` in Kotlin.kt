/**
 * <img src="misc/kt_plus.png" />
 * do not do this !!!
 */
fun main() {
    // if nothing declare
    val result = null + null
    // nullnull
    println(result)
}

data class Vec2(val x: Int, val y: Int)

operator fun Vec2?.plus(other: Vec2?): Vec2 {
    val firstVec = this ?: Vec2(0, 0)
    val secondVec = other ?: Vec2(0, 0)
    return Vec2(firstVec.x + secondVec.x, firstVec.y + secondVec.y)
}
