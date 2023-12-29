import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day15Test {
    @Test
    fun aExample() {
        assertEquals(1320, part15a(getInput()))
    }

    @Test
    fun bExample() {
        assertEquals(145, part15b(getInput()))
    }

    @Test
    fun a() {
        assertEquals(513158, part15a(getInput()))
    }

    @Test
    fun b() {
        assertEquals(200277, part15b(getInput()))
    }
}
