package dev.ikti.core.data.remote

import okhttp3.Interceptor

internal class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val req = chain.request()
        val request = req.newBuilder().url(req.url).build()
        return chain.proceed(request)
    }
}
