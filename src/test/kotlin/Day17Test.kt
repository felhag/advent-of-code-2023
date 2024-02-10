import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day17Test {
    @Test
    fun aExample() {
        assertEquals(102, part17a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(94, part17b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(674, part17a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(773, part17b(getInputs()))
    }
}
