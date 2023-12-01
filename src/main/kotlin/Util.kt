fun getInput(): String {
    return getInputs()[0]
}

fun getInputs(): List<String> {
    return getFileNames().firstNotNullOf(object {}.javaClass::getResourceAsStream).bufferedReader().readLines()
}

private fun getFileNames(): List<String> {
    val stack = Thread.currentThread().stackTrace.first { it.className.startsWith("Day") }
    val method = stack.methodName.lowercase()
    val day = stack.className.substring(3, 5)
    return if (method.contains("example")) {
        val part = method.substring(0, 1)
        listOf("day${day}_example_$part.txt", "day${day}_example.txt")
    } else {
        listOf("day$day.txt")
    }
}
