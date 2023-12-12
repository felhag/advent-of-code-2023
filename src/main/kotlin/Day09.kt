fun part09a(lines: List<String>): Int {
    return lines.sumOf { next(it.split(" ").map(String::toInt)) }
}

fun part09b(lines: List<String>): Int {
    return lines.sumOf { next(it.split(" ").map(String::toInt).reversed()) }
}

private fun next(nums: List<Int>): Int {
    if (nums.all { it == 0 }) {
        return 0
    }
    val diffs = nums.zipWithNext().map { (a, b) -> b - a }
    return nums.last() + next(diffs)
}
