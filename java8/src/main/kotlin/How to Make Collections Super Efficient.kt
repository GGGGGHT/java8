data class Fruit(val name: String, val sugar: Int)


val fruits_ = listOf(
    Fruit("banana", 12),
    Fruit("apple", 10),
    Fruit("origin", 9),
    Fruit("pineapple", 10),
    Fruit("peach", 8),
    Fruit("lemon", 2),
    Fruit("mango", 13),
).sortedBy(Fruit::sugar)


fun main() {
    // 取前两个
    fruits_.take(2).forEach { println(it) }
    println("==")
    // 取后三个
    fruits_.takeLast(3).forEach { println(it) }
    println("==")
    // 丢掉前两个剩下的
    fruits_.drop(2).forEach { println(it) }
    println("==")
    // 丢掉后三后剩下的
    fruits_.dropLast(3).forEach { println(it) }

    val (sweat, superSweet) = fruits_.partition { it.sugar < 10 }
    println(sweat)
    println("==")
    println(superSweet)

    val string = fruits_.reversed().joinToString(
        separator = " + ",
        prefix = "[",
        postfix = "]",
        limit = 3,
        truncated = "MORE",
        transform = Fruit::name
    )

    println(string)
}