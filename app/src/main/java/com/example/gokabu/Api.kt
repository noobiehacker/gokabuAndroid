package com.example.gokabu

import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface API {

    @POST("api/store/login")
    fun login(@Query("username") username: String, @Query("password") password: String) : Observable<LoginResponse>

}