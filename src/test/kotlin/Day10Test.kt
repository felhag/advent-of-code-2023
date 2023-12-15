import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day10Test {
    @Test
    fun aExample() {
        assertEquals(8, part10a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(10, part10b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(6838, part10a(getInputs()))
    }

    @Test
    fun b() {
        // 410 too low
        // 500 too high
        assertEquals(451, part10b(getInputs()))
    }
}
