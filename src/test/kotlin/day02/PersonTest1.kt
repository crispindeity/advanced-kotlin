package day02

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PersonTest1 {

    private val person = Person()

    @Test
    fun isKimTest() {
        // given
        val person = person.apply { name = "김수한무"}

        // when & then
        Assertions.assertThat(person.isKim).isTrue()
    }

    @Test
    fun maskingNameTest() {
        // given
        val person = person.apply { name = "최태현" }

        // when & then
        Assertions.assertThat(person.maskingName).isEqualTo("최**")
    }
}
