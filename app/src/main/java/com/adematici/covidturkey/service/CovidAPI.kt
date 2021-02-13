package com.adematici.covidturkey.service

import com.adematici.covidturkey.model.CovidModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface CovidAPI {

    // https://raw.githubusercontent.com/aticiadem/CovidVerileri/main/veriler.txt
    @GET("aticiadem/CovidVerileri/main/veriler.txt")
    fun getData():Observable<List<CovidModel>>

}