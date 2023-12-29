fun part14a(lines: List<String>): Int {
    val result = lines.map { StringBuilder(it) }
    tilt(result, Direction.N)
    return count(result)
}

fun part14b(lines: List<String>): Int {
    val result = lines.map { StringBuilder(it) }
    val rem = findRemaining(result)
    repeat(rem) { cycle(result) }
    return count(result)
}

private var cache = mutableMapOf<List<String>, Int>()
private fun findRemaining(result: List<StringBuilder>): Int {
    var i = 0
    while (true) {
        val cur = result.map(StringBuilder::toString)
        if (cur in cache) {
            val length = i - cache[cur]!!
            return (1000000000 - i) % length
        }
        cache[cur] = i
        cycle(result)
        i++
    }
}

private fun cycle(result: List<StringBuilder>) {
    Direction.values().forEach { tilt(result, it) }
}

private fun tilt(lines: List<StringBuilder>, dir: Direction): List<StringBuilder> {
    for ((rowIdx, row) in if (dir.rev) lines.withIndex().reversed() else lines.withIndex()) {
        for ((index, col) in if (dir.rev) row.withIndex().reversed() else row.withIndex()) {
            if (col == 'O') {
                move(lines, rowIdx, index, dir)
            }
        }
    }
    return lines
}

private fun move(lines: List<StringBuilder>, row: Int, col: Int, dir: Direction) {
    var r = row
    var c = col
    while(r + dir.row >= 0 && c + dir.col >= 0 && r + dir.row <= lines.lastIndex && c + dir.col <= lines[0].lastIndex && lines[r + dir.row][c + dir.col] == '.') {
        r += dir.row
        c += dir.col
    }
    if (r != row || c != col) {
        lines[r][c] = 'O'
        lines[row][col] = '.'
    }
}

private fun count(result: List<StringBuilder>): Int {
    return result.flatMapIndexed { rowIdx, line -> line.filter { it == 'O' }.map { result[0].length - rowIdx } }.sum()
}

private enum class Direction(val row: Int, val col: Int, val rev: Boolean) {
    N(-1, 0, false),
    W(0, -1, false),
    S(1, 0, true),
    E(0, 1, true);
}
