fun part01a(lines: List<String>): Int {
    return lines.map { it.filter(Char::isDigit) }
        .map { it.first() to it.last() }
        .map { "" + it.first + it.second }
        .sumOf(Integer::parseInt)
}

fun part01b(lines: List<String>): Int {
    return lines.map { find(it, 0..it.length) + find(it, it.length - 1 downTo  0) }.sumOf(Integer::parseInt)
}

fun find(line: String, intRange: IntProgression): String {
    return intRange.iterator().asSequence().firstNotNullOf { findNumber(line, it, intRange.first > intRange.last) }
}

val numbers = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
private fun findNumber(line: String, idx: Int, last: Boolean): String? {
    if (line[idx].isDigit()) return line[idx].toString()
    return numbers.findNumber(last, line, idx)
}

private fun Iterable<String>.findNumber(last: Boolean, line: String, idx: Int): String? {
    val pred = { s: String -> idx + s.length <= line.length && s == line.substring(idx, idx + s.length) };
    val result = if (last) lastOrNull(pred) else firstOrNull(pred)
    return if (result != null) indexOf(result).toString() else null
}
