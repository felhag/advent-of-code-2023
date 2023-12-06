fun part05a(lines: List<String>): Long {
    val maps = lines.subList(2, lines.size)
    return parseLine(lines[0].substringAfter("seeds: ")).minOf { next(it, maps) }
}

fun part05b(lines: List<String>): Long {
    val seeds = parseLine(lines[0].substringAfter("seeds: ")).chunked(2).map { (it[0] until it[0] + it[1]) }
    val maps = mutableListOf(mutableMapOf<LongRange, LongRange>())
    lines.drop(2)
        .reversed()
        .filter{it != ""}
        .forEach{if (it.endsWith("map:")) maps.add(mutableMapOf()) else maps.last().putAll(parseRange(it))}

    var location = 1L
    while (true) {
        val seed = maps.fold(location) { acc, map ->
            val entry = map.entries.find { it.value.contains(acc) }
            if (entry != null) entry.key.first + (acc - entry.value.first) else acc
        }
        if (seeds.any { it.contains(seed) }) {
            return location
        }
        location++
    }
}

private fun parseRange(it: String): Map<LongRange, LongRange> {
    return mapOf( parseLine(it).let { it[1] until it[1] + it[2] to (it[0] until it[0] + it[2]) } )
}

private fun next(num: Long, lines: List<String>): Long {
    print("$num -> ")
    if (lines.isEmpty()) {
        print("[Finished!] $num\n")
        return num
    }
    val split = if (lines.indexOf("") > 0) lines.indexOf("") else lines.size - 1
    val mappings = lines.subList(1, split).map(::parseLine)
    val nextLines = lines.subList(split + 1, lines.size)

    for (mapping in mappings) {
        val source = mapping[1]
        if (num in source..source + mapping[2]) {
            return next(mapping[0] + num - source, nextLines)
        }
    }
    return next(num, nextLines)
}

private fun parseLine(line: String): List<Long> {
    return line.split(" ").map(String::toLong)
}
