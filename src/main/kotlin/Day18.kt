import java.lang.Long.parseLong

fun part18a(lines: List<String>): Int {
    val grid = mutableListOf(mutableListOf(true))
    var pos = Pair(0, 0)
    for (dig in parse(lines)) {
        for (i in 0 until dig.amount) {
            pos = dig.dir.apply(pos)
            if (pos.first < 0) {
                grid.add(0, MutableList(grid[0].size) { false })
                pos = Pair(pos.first + 1, pos.second)
            }
            if (pos.first > grid.lastIndex) {
                grid.add(MutableList(grid[0].size) { false })
            }
            if (pos.second < 0) {
                grid.forEach { it.add(0, false) }
                pos = Pair(pos.first, pos.second + 1)
            }
            if (pos.second > grid[0].lastIndex) {
                grid.forEach { it.add(false) }
            }
            grid[pos.first][pos.second] = true
        }
    }

    fill(grid)

    return grid.sumOf { row -> row.count { it } }
}

fun part18b(lines: List<String>): Long {
    val parse = parse(lines).map { Dig(DigDir.values()[it.color.last().digitToInt()], it.color.dropLast(1).toLong(16), it.color) }
    val corners = parse.scan(Pair(0L,0L)) { acc, pos -> pos.dir.apply(acc, pos.amount) }
    val lava = corners.zipWithNext { a, b -> a.second * b.first - a.first * b.second }.sum() / 2
    val border = parse.sumOf { it.amount }
    return lava + (border / 2) + 1
}

private fun fill( grid: MutableList<MutableList<Boolean>>) {
    val list = mutableListOf(Pair(1, grid[0].indexOf(true) + 1))

    while(list.isNotEmpty()) {
        val pos = list.removeAt(0)
        for (dir in DigDir.values()) {
            val next = dir.apply(pos)
            if (!grid[next.first][next.second]) {
                grid[next.first][next.second] = true
                list.add(next)
            }
        }
    }
}

private fun parse(lines: List<String>): List<Dig> {
    val re = Regex("(\\w) (\\d*) \\(#(\\w*)\\)")
    return lines.map { re.find(it)!!.groupValues }.map { Dig(DigDir.valueOf(it[1]), parseLong(it[2]), it[3]) }
}

private data class Dig(val dir: DigDir, val amount: Long, val color: String)

private enum class DigDir(val row: Int, val col: Int) {
    R(0, 1),
    D(1, 0),
    L(0, -1),
    U(-1, 0);

    fun apply(pos: Pair<Int, Int>): Pair<Int, Int> = Pair(pos.first + row, pos.second + col)
    fun apply(pos: Pair<Long, Long>, amount: Long): Pair<Long, Long> = Pair(pos.first + row * amount, pos.second + col * amount)
}
