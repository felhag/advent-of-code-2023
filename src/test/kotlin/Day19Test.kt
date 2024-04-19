import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day19Test {
    @Test
    fun aExample() {
        assertEquals(19114, part19a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(167409079868000, part19b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(319295, part19a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(0, part19b(getInputs()))
    }
}
