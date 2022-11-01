data class Vec2_(val x: Int, val y: Int) {
    operator fun plus(other: Vec2_?): Vec2_ {
        val otherVec = other ?: Vec2_(0, 0)
        return Vec2_(x + otherVec.x, y + otherVec.y)
    }
}


operator fun Vec2_?.plus(other: Vec2_): Vec2_ {
    return other + this
}

fun main() {
    println(Vec2_(3, 4))
    val anotherVec = null + Vec2_(3, 4)
    println(anotherVec)
}