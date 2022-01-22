package flexta.io.chapter2

fun f(x: Long): Long {
    return x * x // no need for external state
}

fun main(args: Array<String>) {
    var i = 0

    fun g(x: Long): Long {
        return x * i // accessing mutable state
    }

    println(g(1))
    i++
    println(g(1))
    i++
    println(g(1))
}