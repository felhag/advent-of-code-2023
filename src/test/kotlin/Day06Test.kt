import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day06Test {
    @Test
    fun aExample() {
        assertEquals(288, part06a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(71503, part06b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(4568778, part06a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(28973936, part06b(getInputs()))
    }
}
