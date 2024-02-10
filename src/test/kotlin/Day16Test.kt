import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day16Test {
    @Test
    fun aExample() {
        assertEquals(46, part16a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(51, part16b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(6740, part16a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(7041, part16b(getInputs()))
    }
}
