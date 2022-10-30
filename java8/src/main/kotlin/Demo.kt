val fruits = listOf("Apple", "Banana", "Cherry", "Durian")

/**
 * 优化for循环
 */
fun improveLoopsInKotlin() {
    for (index in 0..fruits.size - 1) {
        val fruit = fruits[index]
        println("$index: $fruit")
    }

    for (index in 0 until fruits.size - 1) {
        val fruit = fruits[index]
        println("$index: $fruit")
    }
    for (index in 0..fruits.lastIndex) {
        val fruit = fruits[index]
        println("$index: $fruit")
    }

    for (index in fruits.indices) {
        val fruit = fruits[index]
        println("$index: $fruit")
    }

    for ((index, fruit) in fruits.withIndex()) {
        println("$index: $fruit")
    }

    fruits.forEachIndexed { index, fruit -> println("$index: $fruit") }
}

fun main() {
    improveLoopsInKotlin()

}