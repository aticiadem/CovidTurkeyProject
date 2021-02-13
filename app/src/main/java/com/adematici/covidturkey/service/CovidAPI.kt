package com.adematici.covidturkey.service

import com.adematici.covidturkey.model.CovidModel
import retrofit2.Call
import retrofit2.http.GET

interface CovidAPI {

    // https://raw.githubusercontent.com/aticiadem/CovidVerileri/main/veriler.txt
    @GET("aticiadem/CovidVerileri/main/veriler.txt")
    fun getData():Call<List<CovidModel>>

}