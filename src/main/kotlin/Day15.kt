import java.lang.Integer.parseInt

fun part15a(line: String): Int {
    return line.split(",").map(::hash).sum()
}

fun part15b(line: String): Int {
    val boxes = (0..256).map { LinkedHashMap<String, Int>() }
    line.split(",").map { box(it, boxes) }
    return boxes.flatMapIndexed{ idx, box -> box.entries.mapIndexed { i, entry -> (idx + 1) * (i+1) * entry.value } }.sum()
}

private fun box(s: String, boxes: List<MutableMap<String, Int>>) {
    if (s.contains("=")) {
        val (label, id) = s.split("=")
        val box = boxes[hash(label)]
        box[label] = parseInt(id)
    } else {
        val label = s.substring(0, s.lastIndex)
        boxes[hash(label)].remove(label)
    }
}

private fun hash(s: String): Int {
    return s.runningFold(0) { acc, v -> hash(acc, v) }.last()
}

private fun hash(current: Int, char: Char): Int {
    return (current + char.code) * 17 % 256
}
