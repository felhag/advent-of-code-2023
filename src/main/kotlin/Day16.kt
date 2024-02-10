import kotlin.math.max

fun part16a(lines: List<String>): Int {
    return count(0, -1, 0, 1, lines)
}

fun part16b(lines: List<String>): Int {
    var max = 0
    val length = lines[0].length
    for (idx in lines.indices) {
        println("line $idx/${lines.lastIndex}")
        max = max(max, count(idx, -1, 0, 1, lines))
        max = max(max, count(idx, length, 0, -1, lines))
    }

    for (idx in 0..length) {
        println("col $idx/$length")
        max = max(max, count(-1, idx, 1, 0, lines))
        max = max(max, count(lines.size, idx, -1, 0, lines))
    }
    return max
}

private fun count(row: Int, col: Int, dirRow: Int, dirCol: Int, lines: List<String>): Int {
    val beams = beam(lines, Beam(dirRow, dirCol, mutableListOf(BeamPos(row, col, dirRow, dirCol)))).distinct()
    return lines.mapIndexed { rowIdx, r ->
        r.filterIndexed { colIdx, _ -> beams.any { it.row == rowIdx && it.col == colIdx } }.count()
    }.sum()
}

private fun beam(grid: List<String>, beam: Beam): List<BeamPos> {
    var position = beam.next()
    while (isValid(grid, beam, position)) {
        val char = grid[position.row][position.col]
        val horizontal = beam.dirCol != 0
        val chain = (beam.positions + position).toMutableList()
        val size = chain.size
        if (char == '|' && horizontal) {
            beam.positions.addAll(beam(grid, Beam(-1, 0, chain)).drop(size))
            beam.dir(1, 0, position)
        } else if (char == '-' && !horizontal) {
            beam.positions.addAll(beam(grid, Beam(0, -1, chain)).drop(size))
            beam.dir(0, 1, position)
        } else if (char == '/' && horizontal) {
            beam.dir(beam.dirCol.unaryMinus(), 0, position)
        } else if (char == '/') {
            beam.dir(0, beam.dirRow.unaryMinus(), position)
        } else if (char == '\\' && horizontal) {
            beam.dir(beam.dirCol, 0, position)
        } else if (char == '\\') {
            beam.dir(0, beam.dirRow, position)
        } else {
            beam.dir(beam.dirRow, beam.dirCol, position)
        }
        position = beam.next()
    }
    return beam.positions
}

private fun isValid(grid: List<String>, beam: Beam, position: BeamPos): Boolean {
    if (position.row !in 0..grid.lastIndex || position.col !in 0..grid[0].lastIndex) {
        return false
    }
    // prevent loops
    if (beam.positions.contains(position)) {
        return false
    }
    return true
}

private class Beam(var dirRow: Int, var dirCol: Int, val positions: MutableList<BeamPos>) {
    fun next(): BeamPos {
        val last = last()
        return BeamPos(last.row + dirRow, last.col + dirCol, dirRow, dirCol)
    }

    fun last(): BeamPos {
        return positions.last()
    }

    fun dir(row: Int, col: Int, position: BeamPos) {
        this.dirRow = row
        this.dirCol = col
        this.positions.add(position)
    }
}

private class BeamPos(val row: Int, val col: Int, var dirRow: Int, var dirCol: Int) {
    override fun toString(): String {
        return "[$row, $col] ${if (dirRow > 0) '↓' else if (dirRow < 0) '↑' else if (dirCol > 0) '→' else '←' }"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BeamPos

        if (row != other.row) return false
        if (col != other.col) return false
        if (dirRow != other.dirRow) return false
        if (dirCol != other.dirCol) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + col
        result = 31 * result + dirRow
        result = 31 * result + dirCol
        return result
    }
}
