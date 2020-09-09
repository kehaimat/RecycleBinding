package com.example.databinding

import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUserAsync(): Response<List<User>>
}
