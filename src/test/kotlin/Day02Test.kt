import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day02Test {
    @Test
    fun aExample() {
        assertEquals(8, part02a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(2286, part02b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(2600, part02a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(86036, part02b(getInputs()))
    }
}
