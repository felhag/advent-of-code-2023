import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day01Test {
    @Test
    fun aExample() {
        assertEquals(142, part01a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(281, part01b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(55447, part01a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(54706, part01b(getInputs()))
    }
}
