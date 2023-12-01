fun part01a(lines: List<String>): Int {
    return lines.map { it.filter(Char::isDigit) }
        .map { it.first() to it.last() }
        .map { "" + it.first + it.second }
        .sumOf(Integer::parseInt)
}

fun part01b(lines: List<String>): Int {
    return lines
        .map { find(it, 0..it.length) + find(it, it.length - 1 downTo  0) }
        .sumOf(Integer::parseInt)
}

fun find(line: String, intRange: IntProgression): String {
    for (idx in intRange) {
        val first = findNumber(line, idx, intRange.first > intRange.last)
        if (first != null) {
            return first.toString()
        }
    }
    return ""
}

val numbers = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
private fun findNumber(line: String, idx: Int, last: Boolean): Int? {
    val c = line[idx]
    if (c.isDigit()) {
        return c.digitToInt()
    }
    val first = numbers.firstOrLast(last) { idx + it.length <= line.length && it == line.substring(idx, idx + it.length) }
    if (first != null) {
        return numbers.indexOf(first)
    }
    return null
}

private fun <T> Iterable<T>.firstOrLast(last: Boolean, pred: (T) -> Boolean): T? {
    return if (last) lastOrNull(pred) else firstOrNull(pred)
}
