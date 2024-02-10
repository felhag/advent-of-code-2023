import java.util.*

fun part17a(lines: List<String>): Int {
    return dijkstra(grid(lines), false)
}

fun part17b(lines: List<String>): Int {
    return dijkstra(grid(lines), true)
}

private fun dijkstra(grid: List<List<Int>>, ultra: Boolean): Int {
    val visited = mutableSetOf<Triple<Pair<Int, Int>, DijkstraDirection, Int>>()
    val queue = PriorityQueue(listOf(
        DijkstraPosition(Pair(0, 1), DijkstraDirection.E, grid[0][1], null),
        DijkstraPosition(Pair(1, 0), DijkstraDirection.S, grid[1][0], null)
    ))

    val target = Pair(grid[0].lastIndex, grid.lastIndex)
    while (queue.isNotEmpty()) {
        val pos = queue.remove()
        val sameDir = pos.sameDir()
        val visit = Triple(pos.pos, pos.dir, sameDir)
        if (visited.contains(visit)) {
            continue
        }

        visited.add(visit)

        if (pos.pos == target && (!ultra || sameDir > 2)) {
            return pos.cost
        }
        if ((!ultra && sameDir < 3) || (ultra && sameDir <= 9)) {
            pos.next(queue, pos.dir, grid)
        }
        if (!ultra || (sameDir > 3)) {
            pos.next(queue, pos.dir.prev(), grid)
            pos.next(queue, pos.dir.next(), grid)
        }
    }

    throw RuntimeException("No route found")
}

private class DijkstraPosition(val pos: Pair<Int, Int>, val dir: DijkstraDirection, val cost: Int, val prev: DijkstraPosition?): Comparable<DijkstraPosition> {
    fun sameDir(): Int {
        var result = 1
        var p = prev
        while (p?.dir == dir) {
            p = p.prev
            result++
        }
        return  result
    }

    fun next(queue: PriorityQueue<DijkstraPosition>, dir: DijkstraDirection, grid: List<List<Int>>) {
        val row = pos.first + dir.row
        val col = pos.second + dir.col
        if (row >= 0 && col >= 0 && row <= grid.lastIndex && col <= grid[0].lastIndex) {
            queue.add(DijkstraPosition(Pair(row, col), dir, cost + grid[row][col], this))
        }
    }

    override fun compareTo(other: DijkstraPosition): Int {
        if (cost - other.cost != 0) {
            return cost - other.cost
        }
        return other.pos.first + other.pos.second - pos.first - pos.second
    }

    override fun toString(): String = "(" + pos.first + ", " + pos.second + ", " + dir + ") = " + cost
}

private enum class DijkstraDirection(val row: Int, val col: Int) {
    N(-1, 0),
    E(0, 1),
    S(1, 0),
    W(0, -1);

    fun next(): DijkstraDirection = values()[(ordinal + 1) % 4]
    fun prev(): DijkstraDirection = values()[if (ordinal == 0) 3 else ordinal - 1]
}

private fun grid(lines: List<String>): List<List<Int>> = lines.map { it.map(Char::digitToInt) }
