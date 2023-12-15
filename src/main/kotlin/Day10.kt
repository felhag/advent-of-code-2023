import kotlin.math.absoluteValue

fun part10a(lines: List<String>): Int {
    return findPath(lines).size / 2
}

fun part10b(lines: List<String>): Int {
    val path = findPath(lines)
    val grid = replaceStart(lines, path)
    val start = path.sortedWith(compareBy(DirectedPosition::row, DirectedPosition::col)).first()
    val pathFromLeftTop = path.subList(path.indexOf(start), path.size) + path.subList(0, path.indexOf(start))

    val filled = mutableListOf<Position>()
    val directions = Dir.values()
    val acc = if (start.dir == Dir.E) 1 else -1
    var inside = findInside(directions.indexOf(start.dir), acc)
    for (position in pathFromLeftTop) {
        val currDir = directions.indexOf(position.dir)
        val range = if (acc > 0) (inside.ordinal).downTo(currDir) else inside.ordinal.rangeTo(currDir + acc)
        range.forEach{fill(position.to(directions[it]), grid, pathFromLeftTop, filled)}
        inside = findInside(currDir, acc)
    }

//    draw(lines, pathFromLeftTop, filled, insideLine)

    return filled.size
}

private fun findInside(dir: Int, acc: Int): Dir {
    return when (dir + acc) {
        -1 -> Dir.W
        4 -> Dir.N
        else -> Dir.values()[dir + acc]
    }
}

private fun fill(
    position: Position,
    grid: List<String>,
    path: List<DirectedPosition>,
    filled: MutableList<Position>
) {
    if (filled.any { it.same(position) } || path.any { it.same(position) }) {
        return
    }

    filled.add(position)
    for (dir in Dir.values()) {
        fill(position.to(dir), grid, path, filled)
    }
}

private fun replaceStart(lines: List<String>, path: List<DirectedPosition>): List<String> {
    val first = path.first()
    val start = setOf(first.dir, path.last().dir.opposite())
    val tile = Tile.values().find { start.containsAll(setOf(it.from, it.to)) }!!
    return listOf(lines[first.row].replace('S', tile.char)) + lines.drop(1)
}

private fun findPath(lines: List<String>): MutableList<DirectedPosition> {
    val start = findStart(lines)
    val positions = mutableListOf(start)

    while (true) {
        val next = positions.last().next(lines)
        if (next.same(positions.first())) {
//            draw(lines, positions, listOf())
            return positions
        } else {
            positions.add(next)
        }
    }
}

private fun findStart(lines: List<String>): DirectedPosition {
    val row = lines.indexOfFirst { it.contains("S") }
    val col = lines[row].indexOf('S')
    val start = Position(row, col)

    for (dir in Dir.values()) {
        val next = start.to(dir)
        if (next.row < 0 || next.col < 0) {
            continue
        }
        val tile = next.tile(lines)
        if (tile != null) {
            val opposite = dir.opposite()
            if (tile.to == opposite || tile.from == opposite) {
                return DirectedPosition(row, col, dir)
            }
        }
    }
    throw RuntimeException("Failed to find start")
}

private fun draw(
    grid: List<String>,
    positions: List<Position>,
    filled: List<Position>,
    insideLine: MutableList<Position>
) {
    for ((index, row) in grid.withIndex()) {
        for ((index1, col) in row.withIndex()) {
            val position = Position(index, index1)
            val inside = insideLine.subList(0, 15).any { it.same(position) }
            val path = positions.any { it.same(position) }
            if (positions.subList(0, 15).any { it.same(position) }) {
                print("\u001b[33m$col\u001b[0m")
            } else if (inside) {
                print("\u001b[36m$col\u001b[0m")
            } else if (path) {
                print("\u001b[31m$col\u001b[0m")
            } else if (filled.any { it.same(position) }) {
                print("\u001b[32m$col\u001b[0m")
            } else {
                print(col)
            }
        }
        print("\n")
    }
}

private enum class Dir(val row: Int, val col: Int) {
    N(-1, 0),
    E(0, 1),
    S(1, 0),
    W(0, -1);

    fun opposite(): Dir = Dir.values().first { it.row == negate(row) && it.col == negate(col) }
    fun negate(num: Int): Int = if (num > 0) -num else num.absoluteValue
    fun next(): Dir = values()[(ordinal + 1) % 4]
}

private enum class Tile(val char: Char, val from: Dir, val to: Dir) {
    VERTICAL('|', Dir.N, Dir.S),
    HORIZONTAL('-', Dir.E, Dir.W),
    L('L', Dir.N, Dir.E),
    J('J', Dir.N, Dir.W),
    SEVEN('7', Dir.S, Dir.W),
    F('F', Dir.S, Dir.E),
    S('S', Dir.N, Dir.N)
}

private open class Position(val row: Int, val col: Int) {
    fun to(dir: Dir): Position {
        return Position(row + dir.row, col + dir.col)
    }

    fun tile(grid: List<String>): Tile? {
        return Tile.values().find { it.char == grid[row][col] }
    }

    fun same(position: Position): Boolean {
        return row == position.row && col == position.col
    }

    override fun toString(): String {
        return "[$row][$col]"
    }
}

private class DirectedPosition(row: Int, col: Int, val dir: Dir) : Position(row, col) {
    fun next(grid: List<String>): DirectedPosition {
        val next = to(dir)
        val tile = next.tile(grid)!!
        val opposite = dir.opposite()
        val dir = if (tile.from == opposite) tile.to else tile.from
        return DirectedPosition(next.row, next.col, dir)
    }
}
