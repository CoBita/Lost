package com.bita.lost.net

import com.and.base.log.Log
import com.bita.lost.repo.data.*
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

@Suppress("UNCHECKED_CAST")
class LostResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.isSuccessful) {
            return checkSuccess(response)
        }
        return response
    }

    private fun checkSuccess(response: Response): Response {
        val lostResponse = response.body?.string() ?: "{}"

        val type = object : TypeToken<BaseData<*>>() {}.type

        val responseToJson = Gson().fromJson<BaseData<*>>(lostResponse, type)
                ?: throw JsonSyntaxException("if json is not a valid representation for an object of type")

        val header = responseToJson.response.header

        // 정상 요청이 아닐 시 LostException 을 던진다.
        if (header.code != ErrorType.정상.code) {
            throw LostException(header.code, header.msg)
        }

        val items = responseToJson.response.body.items
        val result = if (items is String) {
            // 공통 Items이 String 일 때 오류처리
            val convertItemsToObject = Body(Any(), responseToJson.response.body.numOfRows, responseToJson.response.body.pageNo, responseToJson.response.body.totalCount)
            Gson().toJson(convertItemsToObject)
        } else if (items is LinkedTreeMap<*, *> && items["item"] is LinkedTreeMap<*, *>) {
            // Items.item이 list 형태가 아니라 object 형태일 때 오류처리
            val temp = items as LinkedTreeMap<String,Any>
            val map = items["item"] as LinkedTreeMap<*, *>
            temp["item"] = arrayListOf<Any>(map)
            val convertItemToObject = Body(temp, responseToJson.response.body.numOfRows, responseToJson.response.body.pageNo, responseToJson.response.body.totalCount)
            Gson().toJson(convertItemToObject)
        } else {
            Gson().toJson(responseToJson.response.body)
                    ?: throw IllegalArgumentException("Response can not convert to Json")
        }

        Log.json(result)

        val mediaType = response.body?.contentType()

        return response.newBuilder().apply {
            body(result.toResponseBody(mediaType))
        }.build()
    }
}

class LostRequestInterceptor(private val serviceKey: String, private val _type: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        val httpUrl = chainRequest.url.newBuilder().apply {
            addEncodedQueryParameter(SERVICE_KEY, serviceKey)
            addQueryParameter(TYPE, _type)
        }.build()
        return chain.proceed(chainRequest.newBuilder().url(httpUrl).build())
    }

    companion object {
        private const val SERVICE_KEY = "serviceKey"
        private const val TYPE = "_type"
    }
}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        val request = chainRequest.newBuilder().apply {
            // Application json 추가
            addHeader("Accept", "application/json")
        }.build()
        return chain.proceed(request)
    }
}
