import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

fun longOperation(): String {
    repeat(2_000_000) { print('.') }

    return "Done"
}

@OptIn(ExperimentalTime::class)
fun main() {
    val (value, time) = measureTimedValue { longOperation() }

    println("It took $time to calculate $value.")
}