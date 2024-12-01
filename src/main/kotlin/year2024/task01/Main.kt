package year2024.task01

import java.lang.Integer.parseInt

fun getResourceAsText(path: String): String? =
    object {}::class.java.classLoader.getResource(path)?.readText()

typealias Data = Pair<MutableList<Int>, MutableList<Int>>

fun loadData(): Data =
    getResourceAsText("01.txt")!!
        .lineSequence()
        .map { it.split("   ", limit = 2) }
        .map { Pair(parseInt(it[0]), parseInt(it[1])) }
        .fold(Pair(mutableListOf<Int>(), mutableListOf<Int>())) { lists, values ->
            lists.first.add(values.first)
            lists.second.add(values.second)
            lists
        }

fun partOne(input: Data) =
    input
        .let { Pair(it.first.sorted(), it.second.sorted()) }
        .let {
            it.first.zip(it.second).map { Math.abs(it.first - it.second) }
        }
        .sum()

fun partTwo(input: Data): Int {
    val histogram = input.second.fold(mutableMapOf<Int, Int>()) { acc, value ->
        acc[value] = 1 + acc.getOrDefault(value, 0)
        acc
    }
    return input.first.sumOf { it*histogram.getOrDefault(it, 0) }
}


fun main() {
    loadData().let { data ->
        partOne(data).also {
            println("part 1 solution = ${it}")
        }
        partTwo(data).also {
            println("part 2 solution = ${it}")
        }
    }
}