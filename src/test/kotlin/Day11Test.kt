import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day11Test {
    @Test
    fun aExample() {
        assertEquals(374, part11a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(82000210, part11b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(9648398, part11a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(618800410814, part11b(getInputs()))
    }
}
