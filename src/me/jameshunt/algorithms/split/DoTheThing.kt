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

    val uniqueChars = s1CharSizeMap.keys + s2CharSizeMap.keys

    val output = uniqueChars
        .map { char ->
            val s1Size = s1CharSizeMap[char] ?: 0
            val s2Size = s2CharSizeMap[char] ?: 0

            when {
                s1Size == s2Size -> Pair("=", char.repeat(s1Size))
                s1Size > s2Size -> Pair("1", char.repeat(s1Size))
                else -> Pair("2", char.repeat(s2Size))
            }
        }
        .sortWithEqualsLast()
        .fold("") { acc, s -> "$acc$s/" }
        .let { it.substring(0, it.length - 1) }

    println(output)
}

fun List<Pair<String, String>>.sortWithEqualsLast(): List<String> {
    val equal = true
    val notEqual = false

    return this
        .groupBy { it.first == "=" }
        .mapValues {
            it.value.sortedWith(Comparator { o1, o2 ->
                when(o1.second.length == o2.second.length) {
                    true -> o1.second.first() - o2.second.first()
                    false -> o2.second.length - o1.second.length
                }
            })
        }
        .let { (it[notEqual]?: emptyList()) + (it[equal]?: emptyList()) }
        .map { (a, b) -> "$a:$b" }
}
