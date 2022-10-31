fun main() {
    val pair = "Gold" to 1
    // val metal = pair.first
    // val placement = pair.second

    // destructuring function
    val (metal, placement) = pair
    val fruit = listOf("Apple", "Banana", "Cherry")
    val (first, second, third) = fruit
    println("${first} ${second} ${third}")
    val medals = mapOf(
        1 to "Gold",
        2 to "Silver",
        3 to "Bronze"
    )

    for ((k, v) in medals) {
        println("${k} ${v}")
    }
}

infix fun <A, B> Pair<A, B>.to(that: Pair<A, B>): Pair<A, B> = Pair(this.first, that.second)