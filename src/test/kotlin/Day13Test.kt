import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day13Test {
    @Test
    fun aExample() {
        assertEquals(405, part13a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(400, part13b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(37561, part13a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(31108, part13b(getInputs()))
    }
}
