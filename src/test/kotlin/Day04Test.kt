import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day04Test {
    @Test
    fun aExample() {
        assertEquals(13, part04a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(30, part04b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(20407, part04a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(23806951, part04b(getInputs()))
    }
}
