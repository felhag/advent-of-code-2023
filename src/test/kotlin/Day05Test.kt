import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day05Test {
    @Test
    fun aExample() {
        assertEquals(35, part05a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(46, part05b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(382895070, part05a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(17729182, part05b(getInputs()))
    }
}
