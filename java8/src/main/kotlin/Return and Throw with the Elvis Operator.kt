fun main() {
    val result = getValueFromApi() ?: error("Invalid value")
    val uppercased = result.uppercase()
}

fun getValueFromApi() : String? {
    return null
}