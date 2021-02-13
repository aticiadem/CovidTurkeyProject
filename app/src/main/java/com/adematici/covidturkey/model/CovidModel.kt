package com.adematici.covidturkey.model

import com.google.gson.annotations.SerializedName

data class CovidModel(
    @SerializedName("eriskinyogunbakimdolulukorani")
    val eriskinyogunbakimdolulukorani: String,
    @SerializedName("hastasayisi")
    val hastasayisi: String,
    @SerializedName("iyilesensayisi")
    val iyilesensayisi: String,
    @SerializedName("tarih")
    val tarih: String,
    @SerializedName("testsayisi")
    val testsayisi: String,
    @SerializedName("toplamagirhastasayisi")
    val toplamagirhastasayisi: String,
    @SerializedName("toplamiyilesensayisi")
    val toplamiyilesensayisi: String,
    @SerializedName("toplamtestsayisi")
    val toplamtestsayisi: String,
    @SerializedName("toplamvakasayisi")
    val toplamvakasayisi: String,
    @SerializedName("toplamvefatsayisi")
    val toplamvefatsayisi: String,
    @SerializedName("vakasayisi")
    val vakasayisi: String,
    @SerializedName("vefatsayisi")
    val vefatsayisi: String,
    @SerializedName("ventilatordolulukorani")
    val ventilatordolulukorani: String,
    @SerializedName("yatakdolulukorani")
    val yatakdolulukorani: String,
    @SerializedName("zaturreorani")
    val zaturreorani: String
)