import kotlin.math.min

fun part13a(lines: List<String>): Int {
    return findGrids(lines).sumOf { findIdentical(it, false) }
}

fun part13b(lines: List<String>): Int {
    return findGrids(lines).sumOf { findIdentical(it, true) }
}

private fun findIdentical(lines: List<String>, smudged: Boolean): Int {
    return (findReflection(lines, smudged).map { it * 100 } + findReflection(lines.transpose(), smudged)).sum()
}

private fun findReflection(lines: List<String>, smudged: Boolean): List<Int> {
    val results = mutableListOf<Int>()
    for (idx in 1 .. lines.lastIndex) {
        val length = min(idx, lines.lastIndex - idx + 1)
        val line1 = lines.subList(idx - length, idx).joinToString()
        val line2 = lines.subList(idx, idx + length).reversed().joinToString()
        val count = line1.filterIndexed { i, char -> char == line2[i] }.count()
        if ((count == line1.length && !smudged) || smudged && count == line1.length - 1) {
            results.add(idx)
        }
    }
    return results
}

private fun findGrids(lines: List<String>): List<List<String>> {
    return lines.mapIndexed { idx, line -> if ( idx == 0 || idx == lines.lastIndex || line.isEmpty()) idx else -1 }
        .filter { it >= 0 }
        .windowed(size = 2, step = 1) { (from, to) -> lines.slice(from..to).filter(String::isNotBlank) }
}

private fun List<String>.transpose(): List<String> {
    return (this[0].indices).map { i -> (this.indices).map { j -> this[j][i] } }.map(List<Char>::toString)
}
