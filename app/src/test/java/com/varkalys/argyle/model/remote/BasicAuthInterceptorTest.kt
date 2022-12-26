package com.varkalys.argyle.model.remote

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import okhttp3.Credentials
import okhttp3.Interceptor
import org.junit.After
import org.junit.Test

class BasicAuthInterceptorTest {

    private val basicAuthInterceptor = BasicAuthInterceptor(USERNAME, PASSWORD)
    private val chain: Interceptor.Chain = mockk(relaxed = true)

    @Test
    fun intercept() {
        basicAuthInterceptor.intercept(chain)
        verify {
            val request = chain.request().newBuilder()
                .header("Authorization", Credentials.basic(USERNAME, PASSWORD))
                .build()
            chain.proceed(request)
        }
    }

    @After
    fun tearDown() {
        confirmVerified(chain)
    }

    companion object {
        private const val USERNAME = "admin"
        private const val PASSWORD = "123456"
    }
}