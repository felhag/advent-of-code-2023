fun log(part1: Any, part2: Any?) {
    val day = getDay()
    println("[Day $day, part one]: $part1")
    if (part2 != null) {
        println("[Day $day, part two]: $part2")
    }
}

fun getInput(): String {
    return getInputs()[0]
}

fun getInputs(): List<String> {
    val fileName = getFileName()
    val resource = object {}.javaClass.getResourceAsStream(fileName)
    return if (resource != null) {
        resource.bufferedReader().readLines()
    } else {
        println("Failed to read file $fileName")
        emptyList()
    }
}

private fun getFileName(): String {
    val day = getDay()
    return "day$day.txt"
}

private fun getDay(): String {
    val stack = Thread.currentThread().stackTrace
    return stack[stack.size - 1].className.substring(3, 5)
}
