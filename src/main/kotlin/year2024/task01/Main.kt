package year2024.task01

import java.lang.Integer.parseInt

fun getResourceAsText(path: String): String? =
    object {}::class.java.classLoader.getResource(path)?.readText()

fun main() {
    val input = getResourceAsText("01.txt")!!

    val sum = input.lineSequence()
        .map { it.split("   ", limit = 2) }
        .map { Pair(parseInt(it[0]), parseInt(it[1])) }
        .fold(
            Pair(mutableListOf<Int>(), mutableListOf<Int>())) {
            lists, values ->
                lists.first.add(values.first)
                lists.second.add(values.second)
                lists
        }
        .let { Pair(it.first.sorted(), it.second.sorted()) }
        .let {
            it.first.zip(it.second).map { Math.abs(it.first - it.second) }
        }
        .sum()

    print(sum)
}