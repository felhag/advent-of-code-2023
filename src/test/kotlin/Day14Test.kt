import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day14Test {
    @Test
    fun aExample() {
        assertEquals(136, part14a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(64, part14b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(109654, part14a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(94876, part14b(getInputs()))
    }
}
