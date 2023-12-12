fun part08a(lines: List<String>): Int {
    val instructions = lines[0]
    val map = lines.drop(2).associate(::parseLine)
    return countPath("AAA", "ZZZ", map, instructions)
}

fun part08b(lines: List<String>): Long {
    val instructions = lines[0]
    val map = lines.drop(2).associate(::parseLine)
    return map.keys
        .filter { it.endsWith("A") }
        .map { countPath(it, "Z", map, instructions).toLong() }
        .reduce{ acc, count -> lcm(acc, count)}
}

private fun countPath(
    startNode: String,
    endNode: String,
    map: Map<String, Pair<String, String>>,
    instructions: String
): Int {
    var node = startNode
    var i = 0
    while (!node.endsWith(endNode)) {
        val pair = map[node]!!
        node = if (instructions[i % instructions.length] == 'L') pair.first else pair.second
        i++
    }
    return i
}

private fun lcm(n1: Long, n2: Long): Long {
    var gcd = 1L
    var i = 1L
    while (i <= n1 && i <= n2) {
        if (n1 % i == 0L && n2 % i == 0L)
            gcd = i
        ++i
    }

    return n1 * n2 / gcd
}

private fun parseLine(line: String): Pair<String, Pair<String, String>> {
    val values = Regex("(\\w{3}) = \\((\\w{3}), (\\w{3})\\)").find(line)!!.groupValues
    return Pair(values[1], Pair(values[2], values[3]));
}
