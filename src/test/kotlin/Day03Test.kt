import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day03Test {
    @Test
    fun aExample() {
        assertEquals(4361, part03a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(467835, part03b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(546312, part03a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(87449461, part03b(getInputs()))
    }
}
