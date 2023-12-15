import kotlin.math.max
import kotlin.math.min

fun part11a(grid: List<String>): Long {
    return calc(grid, 2)
}

fun part11b(grid: List<String>): Long {
    return calc(grid, 1000000)
}

private fun calc(grid: List<String>, amount: Int): Long {
    val (lines, columns) = expand(grid)
    val galaxies = grid.flatMapIndexed { idx, line -> line.mapIndexed { colIdx, char -> if (char == '#') colIdx else -1 }.filter { it >= 0 }.map { Pair(idx, it) } }
    val pairs = galaxies.flatMapIndexed { idx, self -> galaxies.filterIndexed { colIdx, col -> colIdx > idx }.map { calcDistance(self, it, lines, columns, amount) } }
    return pairs.sum()
}

private fun expand(grid: List<String>): Pair<List<Int>, List<Int>> {
    val lines = grid.mapIndexed { idx, line -> if (line.contains('#')) -1 else idx }.filter { it >= 0 }
    val columns = grid[0].mapIndexed { idx, _ -> if (grid.map { it[idx] }.any { it == '#' }) -1 else idx }.filter { it >= 0 }
    return Pair(lines, columns)
}

fun calcDistance(start: Pair<Int, Int>, end: Pair<Int, Int>, lines: List<Int>, columns: List<Int>, amount: Int): Long {
    val fs = min(start.first, end.first)
    val fe = max(start.first, end.first)
    val ss = min(start.second, end.second)
    val se = max(start.second, end.second)
    val multiply = amount.toLong() - 1
    val first = lines.count{ it in fs..fe } * multiply
    val second = columns.count{ it in ss..se } * multiply
    return first + fe - fs + second + se - ss
}
