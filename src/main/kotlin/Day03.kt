import java.lang.Integer.parseInt
import kotlin.math.max
import kotlin.math.min

fun part03a(lines: List<String>): Int {
    val result = mutableListOf<Int>();
    for ((lineIndex, line) in lines.withIndex()) {
        var num = ""
        for ((colIndex, c) in line.withIndex()) {
            if (c.isDigit()) {
                num += c
            } else if (num != "") {
                if (hasAdjecentSymbol(lines, lineIndex, colIndex, num)) result.add(parseInt(num))
                num = ""
            }
        }
        if (num != "" && hasAdjecentSymbol(lines, lineIndex, line.length - 1, num)) {
            result.add(parseInt(num))
        }
    }
    return result.sum()
}

fun part03b(lines: List<String>): Int {
    val result = mutableListOf<Int>()
    for ((lineIndex, line) in lines.withIndex()) {
        for ((colIndex, c) in line.withIndex()) {
            if (c == '*') {
                val adjecentNumbers = getAdjecentNumbers(lines, lineIndex, colIndex)
                if (adjecentNumbers.size == 2) {
                    result.add(adjecentNumbers.reduce{acc, num -> acc * num})
                }
            }
        }
    }
    return result.sum()
}

private fun hasAdjecentSymbol(grid: List<String>, row: Int, col: Int, num: String): Boolean {
    for (rowIndex in max(row - 1, 0).. min(row + 1, grid.size - 1)) {
        for (colIndex in max(col - num.length - 1, 0) .. min (col, grid[0].length)) {
            val char = grid[rowIndex][colIndex]
            if (char != '.' && !char.isDigit()) {
                return true
            }
        }
    }
    return false
}

private fun getAdjecentNumbers(grid: List<String>, row: Int, col: Int): Set<Int> {
    val result = mutableSetOf<Int>()
    for (rowIndex in max(row - 1, 0).. min(row + 1, grid.size - 1)) {
        for (colIndex in max(col - 1, 0)..min(col + 1, grid[0].length)) {
            val char = grid[rowIndex][colIndex]
            if (char.isDigit()) {
                result.add(getFullNumber(grid[rowIndex], colIndex))
            }
        }
    }
    return result
}

private fun getFullNumber(line: String, col: Int): Int {
    var start = col
    var end = col
    while(start > 0 && line[start - 1].isDigit()) start--
    while(end < line.length - 1 && line[end + 1].isDigit()) end++
    return parseInt(line.substring(start, end + 1))
}
