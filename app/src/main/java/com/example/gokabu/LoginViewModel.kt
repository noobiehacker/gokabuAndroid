package com.example.gokabu

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel : ViewModel() {

    fun login(userName : String, password : String): Observable<LoginResponse> {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api2-dev.kabu.ca/kabueats/jack/delivery/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var api = retrofit.create(API::class.java)
        return api.login(userName, password)
    }

}