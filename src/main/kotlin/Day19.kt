import java.lang.Integer.parseInt

fun part19a(lines: List<String>): Int {
    return parts(lines).filter { run("in", flows(lines), it) }.sumOf { it.values.sum() }
}

fun part19b(lines: List<String>): Int {
    val flows = flows(lines)
    val ranges = "xmas".associateWith { 1 until 4000 }
    val result = run("in", flows, ranges)

    return result.values.fold(1) { acc, range -> acc * (range.last - range.first + 1) }
}

private fun run(flow: String, flows: Map<String, List<Workflow>>, ranges: Map<Char, IntRange>): Map<Char, IntRange> {
    if (flow == "R") {
        return emptyMap()
    } else if (flow == "A") {
        return ranges
    }

    val result = mutableMapOf<Char, IntRange>()
    for (f in flows[flow]!!) {
        val copy = ranges.toMutableMap()
        if (f.amount == null) {
            result.putAll(run(f.then, flows, copy))
        } else {
            result.putAll(f.range(copy))
        }
    }
    return result
}

private fun run(flow: String, flows: Map<String, List<Workflow>>, part: Map<Char, Int>): Boolean {
    val next = flows[flow]!!.find { it.match(part) }!!.then
    return when (next) {
        "A" -> true
        "R" -> false
        else -> run(next, flows, part)
    }
}

private fun parts(lines: List<String>): List<Map<Char, Int>> =
    lines.subList(lines.indexOf("") + 1, lines.size).map { line -> line.itemsBetween('{', '}').associateBy(
        { it[0] },
        { parseInt(it.substringAfter('=')) }
    ) }

private fun flows(lines: List<String>): Map<String, List<Workflow>> =
    lines.subList(0, lines.indexOf("")).associateBy(
        { it.substringBefore('{') },
        { it.itemsBetween('{', '}').map(::createWorkflow) }
    )

private fun createWorkflow(input: String): Workflow {
    val split = input.split(':')
    return if (split.size > 1) {
        Workflow(split[1], input.first(), input[1] == '>', parseInt(split[0].drop(2)))
    } else {
        Workflow(input, null, null, null)
    }
}

private class Workflow(val then: String, val part: Char?, val gt: Boolean?, val amount: Int?) {
    fun match(categories: Map<Char, Int>): Boolean = amount == null || gt == categories[part]!! > amount
    fun range(categories: MutableMap<Char, IntRange>): MutableMap<Char, IntRange> {
        if (gt!!) {
            categories[part!!] = (amount!! + 1) until categories[part]!!.last
        } else {
            categories[part!!] = categories[part]!!.first until amount!! - 1
        }
        return categories
    }
}

//private fun Map<Char, IntRange>.copy(): Map<Char, IntRange> = toMap()
private fun String.substringBetween(a: Char, b: Char): String = substringAfter(a).substringBefore(b)
private fun String.itemsBetween(a: Char, b: Char): List<String> = substringBetween(a, b).split(',')
