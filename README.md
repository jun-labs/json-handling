# Json Handling

Gson과 Jackson 라이브러리를 통한 Json 객체 핸들링.

<br/>
<br/>
<br/>

## 💻 프로그램 실행

app 모듈의 ExampleTest.kts에, resource 폴더 내 weather.json 파일경로 지정.

````kotlin
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
````

<br>
<br>
<br>
<br>

빌드 후 실행.

```shell
# 빌드
./gradlew :app:build
```

<br>
<br>
<br>
<br>
<br>
<br>

## 📝 Json 데이터 핸들링

`Gson` 또는 `Jackson` 라이브러리를 사용해 Json 데이터를 배열로 만든 후, 데이터를 추가/삭제할 수 있다. 

```kotlin
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
```

```kotlin
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
```

<br>
<br>
<br>
<br>

이를 통해 빌드 시, 부가 작업이 가능하며, buildSrc을 통해 코드를 재활용할 수 있다.

```shell
├── buildSrc
│ ├── build
│ │ ├── classes
│ │ │ └── java
│ │ │     └── main
│ │ │         └── ScriptUtils.class
│ │ ├── generated
│ │ │ └── sources
│ │ │     ├── annotationProcessor
│ │ │     │ └── java
│ │ │     └── headers
│ │ │         └── java
│ │ ├── libs
│ │ │ └── buildSrc.jar
│ │ └── tmp
│ │     ├── compileJava
│ │     │ └── previous-compilation-data.bin
│ │     └── jar
│ │         └── MANIFEST.MF
│ ├── build.gradle.kts
│ └── src
│     └── main
│         └── java
│             └── ScriptUtils.java  # 빌드 스크립트에서 활용 가능

    ......
    
```
