data class Cat(val name:String, val sound: String, val photo: String)

val cats = listOf(
    Cat("Mono", "meow", "mono_dancing.jpg"),
    Cat("", "purr", "cat_sleeping.jpg"),
    Cat("Bella", "MEOW", "bella_hunting.jpg"),
)

fun main(){
    cats.map { it.photo.removeSuffix(".jpg") }

    cats.map { it.photo.removeSuffix(".jpg") }

    "__MEOW__".removeSurrounding("__")

    cats.map {it .name.ifBlank { "the Cat" }}

    cats.first().sound.equals(cats.last().sound,true)
}