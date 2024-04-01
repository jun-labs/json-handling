package project.gson.app.test.unittest

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[UnitTest] GSon 단위 테스트")
class GsonUnitTest {

    private lateinit var gson: Gson

    @BeforeEach
    fun setUp() {
        gson = Gson()
    }

    @Test
    @DisplayName("JSon 객체에 데이터를 추가할 수 있다.")
    fun objAdditionalTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7}"
        val jsonObj = createObj(jsonStr)

        jsonObj.addProperty("city", "SEOUL")

        val result = Gson().toJson(jsonObj)
        val expected = "{\"name\":\"Jun\",\"age\":7,\"city\":\"SEOUL\"}"

        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Json 객체에 배열 데이터를 추가할 수 있다.")
    fun jsonArrayAdditionalTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7}"
        val jsonObj = createObj(jsonStr)

        val jsonArray = JsonArray().apply {
            add("item1")
            add("item2")
        }
        jsonObj.add("items", jsonArray)

        val result = gson.toJson(jsonObj)
        val expected = "{\"name\":\"Jun\",\"age\":7,\"items\":[\"item1\",\"item2\"]}"

        assertEquals(expected, result)
    }

    @Test
    @DisplayName("Json 객체에 데이터를 읽어올 수 있다.")
    fun jsonDataReadTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7}"
        val jsonObj = createObj(jsonStr)

        val findName = jsonObj.get("name").asString
        val expected = "Jun"

        assertEquals(expected, findName)
    }

    @Test
    @DisplayName("Json 객체에 배열 데이터를 읽을 수 있다.")
    fun jsonArrayReadTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7}"
        val jsonObj = createObj(jsonStr)

        val jsonArray = JsonArray().apply {
            add("item1")
            add("item2")
        }
        jsonObj.add("items", jsonArray)

        val result = jsonObj.getAsJsonArray("items")

        assertEquals("item1", result.get(0).asString)
        assertEquals("item2", result.get(1).asString)
    }

    @Test
    @DisplayName("Json 객체에서 데이터를 삭제할 수 있다.")
    fun jsonDataRemoveTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7}"
        val jsonObj = createObj(jsonStr)

        val jsonArray = JsonArray().apply {
            add("item1")
            add("item2")
        }
        jsonObj.add("items", jsonArray)
        jsonObj.remove("items")

        val result = Gson().toJson(jsonObj)
        val expected = "{\"name\":\"Jun\",\"age\":7}"

        assertEquals(expected, result)
    }

    @Test
    @DisplayName("추가된 데이터 순서를 보장한다.")
    fun dataOrderTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7}"
        val jsonObj = createObj(jsonStr)

        val jsonArray = JsonArray().apply {
            add("item2")
            add("item1")
        }
        jsonObj.add("items", jsonArray)

        val result = Gson().toJson(jsonObj)
        val expected = "{\"name\":\"Jun\",\"age\":7,\"items\":[\"item2\",\"item1\"]}"

        assertEquals(expected, result)
    }

    @Test
    @DisplayName("추가된 데이터의 순서를 변경하면 올바르지 않은 결과가 나올 수 있다..")
    fun dataOrderFailureTest() {
        val jsonStr = "{\"name\":\"Jun\", \"age\":7}"
        val jsonObj = createObj(jsonStr)

        val jsonArray = JsonArray().apply {
            add("item2")
            add("item1")
        }
        jsonObj.add("items", jsonArray)

        val result = Gson().toJson(jsonObj)
        val expected = "{\"name\":\"Jun\",\"age\":7,\"items\":[\"item1\",\"item2\"]}"

        assertNotEquals(expected, result)
    }

    private fun createObj(jsonString: String): JsonObject {
        return gson.fromJson(jsonString, JsonObject::class.java)
    }
}
