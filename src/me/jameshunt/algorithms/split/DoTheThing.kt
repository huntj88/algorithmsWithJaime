package me.jameshunt.algorithms.split

// https://www.codewars.com/kata/strings-mix

const val s1 = "my&friend&Paul has heavy hats! &"
const val s2 = "my friend John has many many friends &"

fun main() {

    fun String.mapCharSize() = this
        .split("")
        .groupBy { it }
        .mapValues { it.value.count() }
        .filterKeys { it.firstOrNull() in 'a'..'z' }
        .filterValues { it > 1 }

    val s1CharSizeMap = s1.mapCharSize()
    val s2CharSizeMap = s2.mapCharSize()

    val allChars = s1CharSizeMap.keys + s2CharSizeMap.keys

    val output = allChars
        .map {
            val first = s1CharSizeMap[it] ?: 0
            val second = s2CharSizeMap[it] ?: 0

            when {
                first == second -> "=:" to it.repeat(first)
                first > second -> "1:" to it.repeat(first)
                else -> "2:" to it.repeat(second)
            }
        }
        .groupBy { !it.first.contains('=') }
        .mapValues {
            it.value.sortedWith(Comparator { o1, o2 ->
                if(o1.second.length == o2.second.length) {
                    o1.second.first() - o2.second.first()
                } else {
                    o2.second.length - o1.second.length
                }
            })
        }
        .let { (it[true]?: emptyList()) + (it[false]?: emptyList()) }
        .map { (a, b) -> a + b }
        .fold("") { acc, s -> "$acc$s/" }
        .let { it.substring(0, it.length - 1) }

    println(output)
}
