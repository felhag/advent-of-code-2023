import kotlin.math.pow

fun part07a(lines: List<String>): Int {
    return parseHands(lines, false)
}

fun part07b(lines: List<String>): Int {
    return parseHands(lines, true)
}

private fun parseHands(lines: List<String>, jokers: Boolean): Int {
    return lines.map { it.split(" ") }
        .associate { Pair(parseHand(it[0], jokers), it[1].toInt()) }
        .toSortedMap(reverseOrder())
        .values
        .reduceIndexed { idx, acc, bid -> ((idx + 1) * bid) + acc }
}

val cardsA = (2..9).map{it.toString().single()} + listOf('T', 'J', 'Q', 'K', 'A')
val cardsB = listOf('J') + (2..9).map{it.toString().single()} + listOf('T', 'Q', 'K', 'A')
val types = listOf<(counts: Map<Char, Int>) -> Boolean>(
    { it.size == 1 },
    { it.containsValue(4) },
    { it.size == 2 && it.containsValue(3) },
    { it.containsValue(3) },
    { it.count { c -> c.value == 2 } == 2 },
    { it.containsValue(2) },
    { true },
)
private fun parseHand(hand: String, jokers: Boolean): Double {
    val cards = (if (jokers) cardsB else cardsA).reversed()
    val secondary = hand.mapIndexed { idx, c -> cards.indexOf(c).toDouble() / (10).toDouble().pow(((idx + 1) * 2).toDouble()) }.sum()
    val counts = hand.associateWith { h -> hand.count { it == h } }.toMutableMap()
    if (jokers && counts.containsKey('J') && counts.size > 1) {
        val max = counts.filter{it.key != 'J'}.maxBy { it.value }
        counts[max.key] = counts[max.key]!! + counts['J']!!
        counts.remove('J')
    }
    val type = types.indexOfFirst { type -> type(counts) }.toDouble()
    return type + secondary
}
