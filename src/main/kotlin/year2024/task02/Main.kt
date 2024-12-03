package year2024.task02

import java.lang.Integer.parseInt
import kotlin.math.abs

fun getResourceAsText(path: String): String? =
    object {}::class.java.classLoader.getResource(path)?.readText()

typealias Data = Sequence<List<Int>>

fun loadData(): Data =
    getResourceAsText("02.txt")!!.trim()
        .lineSequence()
        .map { it.split(" ").map { parseInt(it) } }

enum class Direction { UP, DOWN, UNDETERMINED }

data class FoldState(
    val success: Boolean = false,
    val direction: Direction,
    val lastNumber: Int = 0,
)

fun conditionMet(list: List<Int>) =
    list.foldIndexed(
        FoldState(true, Direction.UNDETERMINED),
        { index, state, number ->
            if (!state.success) { state }
            else {
                val firstRun = index == 0
                val distance = number - state.lastNumber
                val direction = when {
                    firstRun -> Direction.UNDETERMINED
                    distance > 0 -> Direction.UP
                    else -> Direction.DOWN
                }
                val distanceAllowed = abs(distance) in 1..3
                val directionAllowed = direction == state.direction || state.direction == Direction.UNDETERMINED
                FoldState(firstRun || (directionAllowed && distanceAllowed), direction, number)
            }
        }
    ).success

fun taskOne(data: Data): Int = data.map { conditionMet(it) }.sumOf { (if (it) 1 else 0) as Int }

fun main() {
    loadData().let { taskOne(it) }.also { println("Task one: $it") }
}