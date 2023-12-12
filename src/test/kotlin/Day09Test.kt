import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day09Test {
    @Test
    fun aExample() {
        assertEquals(114, part09a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(2, part09b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(1725987467, part09a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(971, part09b(getInputs()))
    }
}
