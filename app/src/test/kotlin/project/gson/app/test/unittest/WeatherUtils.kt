package project.gson.app.test.unittest

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject

fun add(content: String): String {
    val gson = Gson()
    val jsonObject = gson.fromJson(content, JsonObject::class.java)

    jsonObject.getAsJsonObject("locations").entrySet().forEach { city ->
        city.value.asJsonObject.entrySet().forEach { dong ->
            val traffic = JsonArray().apply {
                add(
                    JsonObject()
                        .apply {
                            add("bus", JsonArray())
                        }
                )
            }
            dong.value.asJsonObject.add("traffic", traffic)
        }
    }
    return gson.toJson(jsonObject)
}
