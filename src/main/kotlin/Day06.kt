fun part06a(lines: List<String>): Int {
    val (time, distance) = lines.map { line -> line.split(" ").filter { it != "" }.drop(1).map(String::toLong) }

    return time.indices
        .map{ i -> (0 until time[i]).count { race(time[i], distance[i], it) } }
        .reduce{acc, i -> acc * i}
}

fun part06b(lines: List<String>): Int {
    val (time, distance) = lines.map { line -> line.substringAfter(":").filter { it != ' ' }.toLong() }
    return (0 until time).count { race(time, distance, it) }
}

private fun race(time: Long, distance: Long, hold: Long): Boolean {
    return (time - hold) * hold > distance
}
