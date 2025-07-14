package com.nakersolutionid.suitmedia.data.remote.network

import com.nakersolutionid.suitmedia.data.remote.response.GetUsersResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiServices {
    @GET("users")
    suspend fun getUsers(
        @Header("x-api-key") apiKey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 5
    ) : GetUsersResponse
}