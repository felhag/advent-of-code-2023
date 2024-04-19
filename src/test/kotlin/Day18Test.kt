import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day18Test {
    @Test
    fun aExample() {
        assertEquals(62, part18a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(952408144115, part18b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(62365, part18a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(159485361249806, part18b(getInputs()))
    }
}
