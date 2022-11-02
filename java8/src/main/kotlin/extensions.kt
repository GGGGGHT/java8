import cn.hutool.core.lang.UUID

fun main() {
    applyAction("hello", "bye") { s: String -> println(s.uuid()) }
}

fun applyAction(vararg string: String, action: (String) -> Unit) = string.forEach(action)
/**
 * extension function
 */
private fun String.uuid(): String = UUID.nameUUIDFromBytes(this.encodeToByteArray()).toString()
