package com.example.bhartiyarail.network

import com.example.bhartiyarail.BuildConfig
import com.google.gson.Gson
import okhttp3.*


const val pnrResponse = """{"pnr_status":[{"train_name":"Goa","train_number":"4436","passengers":40,"time":"15/09/2020 06:30 PM"},{"train_name":"Goa","train_number":"7744","passengers":40,"time":"15/09/2020 06:30 PM"},{"train_name":"Goa","train_number":"2232","passengers":40,"time":"15/09/2020 06:30 PM"},{"train_name":"Goa","train_number":"1634","passengers":40,"time":"15/09/2020 06:30 PM"},{"train_name":"Goa","train_number":"1352","passengers":40,"time":"15/09/2020 06:30 PM"},{"train_name":"Goa","train_number":"1658","passengers":40,"time":"15/09/2020 06:30 PM"},{"train_name":"Goa","train_number":"2654","passengers":40,"time":"15/09/2020 06:30 PM"},{"train_name":"Goa","train_number":"0253","passengers":40,"time":"15/09/2020 06:30 PM"},{"train_name":"Goa","train_number":"4123","passengers":40,"time":"15/09/2020 06:30 PM"}]}"""
const val slideShowResponse = """{"train_name":"Goa","train_number":"1234","source":"", "destination": "","time":"15/09/2020 06:30 PM"}"""
class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            val responseString = when {
                uri.contains("pnrStatus.json") -> {
                    val gson = Gson()
                    val string = gson.fromJson(pnrResponse, PnrStatusResponse::class.java)
                    val response = string.pnrStatus.filter { it.trainNumber == uri.substring(uri.lastIndexOf("=") + 1, uri.length)}
                    gson.toJson(response).toString()
                }
                uri.endsWith("sourceDest.json") -> slideShowResponse
                else -> ""
            }

            return chain.proceed(chain.request())
                    .newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(responseString)
                    .body(
                            ResponseBody.create(
                                    MediaType.parse("application/json"),
                                    responseString.toByteArray()))
                    .addHeader("content-type", "application/json")
                    .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }
}