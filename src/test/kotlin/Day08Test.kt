import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day08Test {
    @Test
    fun aExample() {
        assertEquals(6, part08a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(6, part08b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(11567, part08a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(9858474970153, part08b(getInputs()))
    }
}
