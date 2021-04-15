package com.example.meuamigoco

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCachorros {

    @GET("{id}")
    fun get(@Path("id")id:Int):Call<Cachorro>
}