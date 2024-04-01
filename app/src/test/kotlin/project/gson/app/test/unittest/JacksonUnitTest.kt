package project.gson.app.test.unittest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[UnitTest] Jackson 단위 테스트")
class KotlinJsonUnitTest {

    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        objectMapper = ObjectMapper()
    }

    @Test
    @DisplayName("Json 객체에 데이터를 추가할 수 있다.")
    fun objAdditionalTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7}"
        val obj = objectMapper.readValue<Map<String, Any>>(jsonStr)
        val newObj = obj.plus(Pair("city", "SEOUL"))

        val result = objectMapper.writeValueAsString(newObj)
        val expected = "{\"name\":\"Jun\",\"age\":7,\"city\":\"SEOUL\"}"

        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Json 객체에 배열 데이터를 추가할 수 있다.")
    fun jsonArrayAdditionalTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7}"
        val obj = objectMapper.readTree(jsonStr) as ObjectNode

        val jsonArray = objectMapper.createArrayNode().apply {
            add("item1")
            add("item2")
        }
        obj.set<ArrayNode>("items", jsonArray)

        val result = objectMapper.writeValueAsString(obj)
        val expected = "{\"name\":\"Jun\",\"age\":7,\"items\":[\"item1\",\"item2\"]}"

        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Json 객체에서 데이터를 삭제할 수 있다.")
    fun jsonDataRemoveTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7, \"items\":[\"item1\",\"item2\"]}"
        val jsonObj = objectMapper.readValue<Map<String, Any>>(jsonStr)

        val newJson = jsonObj.minus("items")

        val result = objectMapper.writeValueAsString(newJson)
        val expected = "{\"name\":\"Jun\",\"age\":7}"

        assertEquals(expected, result)
    }

    @Test
    @DisplayName("추가된 데이터 순서를 보장한다.")
    fun dataOrderTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7}"
        val jsonObj = objectMapper.readTree(jsonStr) as ObjectNode

        jsonObj.put("city", "SEOUL")

        val itemsArray = objectMapper.createArrayNode().apply {
            add("item2")
            add("item1")
        }
        jsonObj.set<ArrayNode>("items", itemsArray)

        val result = objectMapper.writeValueAsString(jsonObj)
        val expected = "{\"name\":\"Jun\",\"age\":7,\"city\":\"SEOUL\",\"items\":[\"item2\",\"item1\"]}"

        assertEquals(expected, result)
    }
}

