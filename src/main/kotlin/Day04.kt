import kotlin.math.pow

fun part04a(lines: List<String>): Int {
    return lines.map(::parseCard).filter { it > 0 }.sumOf { 2.0.pow(it - 1.toDouble()).toInt() }
}

fun part04b(lines: List<String>): Int {
    val counts = List(lines.size){1}.toMutableList()
    lines.map(::parseCard).forEachIndexed { idx, score ->
        val count = counts[idx]
        repeat(score) { counts[idx + it + 1] += count }
    }
    return counts.sum()
}

private fun parseCard(line: String): Int {
    val numbers = line.substringAfter(":").split(" | ").map(::parseNumbers)
    val card = Pair(numbers[0], numbers[1])
    return card.second.count { card.first.contains(it) }
}

private fun parseNumbers(numbers: String): List<Int> {
    return numbers.trim().split(" ").map(String::trim).filterNot(String::isEmpty).map(Integer::parseInt)
}
