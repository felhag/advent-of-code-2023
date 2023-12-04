import java.lang.Integer.parseInt
import kotlin.math.max

fun part02a(lines: List<String>): Int {
    val max = Cubes(12, 13, 14)
    return parse(lines).filterValues { c -> c.none { it.red > max.red || it.green > max.green || it.blue > max.blue } }.keys.sum()
}

fun part02b(lines: List<String>): Int {
    return parse(lines).values.map(::findMin).sum()
}

private fun findMin(entry: List<Cubes>): Int {
    val min = Cubes(0,0,0)
    entry.forEach{
        min.red = max(min.red, it.red)
        min.green = max(min.green, it.green)
        min.blue = max(min.blue, it.blue)
    }
    return min.red * min.green * min.blue
}

private fun parse(lines: List<String>): Map<Int, List<Cubes>> {
    return lines.associate(::parseLine)
}

private fun parseLine(line: String): Pair<Int, List<Cubes>> {
    val split = line.split(": ")
    val game = parseInt(split[0].substring(5))
    val games = split[1].split("; ").map(::parseGame)
    return Pair(game, games)
}

private fun parseGame(game: String): Cubes {
    val values = game.split(", ").map { it.split(" ") }.associateBy({ it[1] }, { parseInt(it[0]) })
    return Cubes(
        values.getOrDefault("red", 0),
        values.getOrDefault("green", 0),
        values.getOrDefault("blue", 0)
    )
}

private data class Cubes(var red: Int, var green: Int, var blue: Int) {
}
