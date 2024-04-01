package project.gson.app.test.unittest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File

@DisplayName("[UnitTest] 날씨 예제 단위 테스트")
class ExampleUnitTest {

    @Test
    @DisplayName("Json 파일에 데이터를 추가할 수 있다.")
    fun trafficAdditionalTest() {
        val filePath = "PATH"

        val fileContent = File(filePath).readText()
        val result = add(fileContent)

        assertThat(result).contains("traffic")
    }
}
