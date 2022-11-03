import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JunitTest {

    @Test
    fun `test one plus one` () {
        assertThat(1+1).isEqualTo(2)
    }
}
