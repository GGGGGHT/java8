fun getName(): String? {
    return null
}

fun main() {
    val name = getName()
    val greeting = name ?: run {
        println("Oops, name was not set")
        "undefined"
    }
}