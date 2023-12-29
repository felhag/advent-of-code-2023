import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Day12Test {
    @Test
    fun aExample() {
        assertEquals(21, part12a(getInputs()))
    }

    @Test
    fun bExample() {
        assertEquals(525152, part12b(getInputs()))
    }

    @Test
    fun a() {
        assertEquals(7163, part12a(getInputs()))
    }

    @Test
    fun b() {
        assertEquals(17788038834112, part12b(getInputs()))
    }
}
