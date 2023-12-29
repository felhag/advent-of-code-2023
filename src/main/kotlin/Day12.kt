fun part12a(lines: List<String>): Long {
    return lines.sumOf {
        val (springs, instructions) = it.split(" ")
        val parsed = instructions.split(",").map(String::toInt)
        iterate(springs, parsed)
    }
}

fun part12b(lines: List<String>): Long {
    var i = 0
    return lines.sumOf {
        val (springs, instructions) = it.split(" ")
        val multiplied = (0..4).joinToString("?") { springs }
        val parsed = (0..4).joinToString(",") { instructions }.split(",").map(String::toInt)
        i++
        iterate(multiplied, parsed)
    }
}

private var cache = mutableMapOf<Pair<String, List<Int>>, Long>()
private fun iterate(springs: String, instructions: List<Int>): Long {
    if (instructions.isEmpty()) {
        return if (springs.none{ it.isDamaged()}) 1 else 0
    }
    if (springs.isBlank() || springs.length < instructions.sum()) {
        return 0
    }
    if (springs[0].isDamaged()) {
        val size = instructions.first()
        if (springs.substring(0, size).any { it.isOperational() } || (springs.length > size && springs[size].isDamaged())) {
            return 0
        }
        val key = Pair(springs.drop(size + 1), instructions.drop(1))
        return cache.getOrPut(key) { iterate(key.first, key.second) }
    }

    val dropFirst = springs.drop(1)
    if (springs[0].isOperational()) {
        return cache.getOrPut(Pair(dropFirst, instructions)) { iterate(dropFirst, instructions) }
    }

    val damaged = cache.getOrPut(Pair("#$dropFirst", instructions)) { iterate("#$dropFirst", instructions) }
    val operational = cache.getOrPut(Pair(".$dropFirst", instructions)) { iterate(".$dropFirst", instructions) }
    return damaged + operational
}

private fun Char.isDamaged() = this =='#'
private fun Char.isOperational() = this == '.'
