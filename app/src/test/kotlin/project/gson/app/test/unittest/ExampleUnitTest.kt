package project.gson.app.test.unittest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class ExampleUnitTest {

    @Test
    fun trafficAdditionalTest() {
        val filePath = "PATH"

        val fileContent = File(filePath).readText()
        val result = add(fileContent)

        assertThat(result).contains("traffic")
    }
}
