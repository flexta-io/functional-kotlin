package flexta.io.chapter2

import java.util.*

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

    println(capitalize("hello world"))
    println(transform("kotlin", capitalize))
    println(transform("kotlin", ::reverse))
    println(transform("kotlin", MyUtils::doNothing))

    val transformer = Transformer()
    println(transform("kotlin", transformer::upperCased))
    println(transform("kotlin", Transformer.Companion::lowerCased))

    println(transform("kotlin") { str -> str.substring(0..1) })
    println(transform("kotlin", { it.substring(0..1) }))

    val securityCheck = false // some interesting code here
    unless(securityCheck) {
        println("You can't access this website")
    }

    useMachine(5, PrintMachine())

//    useMachine(5, object : Machine<Int> {
//        override fun process(t: Int) {
//            println(t)
//        }
//    })
    useMachine(5, ::println)

    useMachine(5) { i ->
        println(i)
    }
}

val capitalize = { str: String ->
    str.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else
            it.toString()
    }
}

fun <T> transform(t: T, fn: (T) -> T): T {
    return fn(t)
}

fun reverse(str: String): String {
    return str.reversed()
}

object MyUtils {
    fun doNothing(str: String): String {
        return str
    }
}

class Transformer {
    fun upperCased(str: String): String {
        return str.uppercase(Locale.getDefault())
    }

    companion object {
        fun lowerCased(str: String): String {
            return str.lowercase(Locale.getDefault())
        }
    }
}

fun unless(condition: Boolean, block: () -> Unit) {
    if (!condition) block()
}

//interface Machine<T> {
//    fun process(product: T)
//}
//
//fun <T> useMachine(t: T, machine: Machine<T>) {
//    machine.process(t)
//}
//
//class PrintMachine<T> : Machine<T> {
//    override fun process(product: T) {
//        println(product)
//    }
//}

typealias Machine<T> = (T) -> Unit

fun <T> useMachine(t: T, machine: Machine<T>) {
    machine(t)
}

class PrintMachine<T> : Machine<T> {
    override fun invoke(p1: T) {
        println(p1)
    }
}