import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day07Test {
    @Test
    fun aExample() {
        assertEquals(6440, part07a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(5905, part07b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(250058342, part07a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(250506580, part07b(getInputs()))
    }
}
