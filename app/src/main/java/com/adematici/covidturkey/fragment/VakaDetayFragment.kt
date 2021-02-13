package com.adematici.covidturkey.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.adematici.covidturkey.R
import com.adematici.covidturkey.databinding.FragmentVakaDetayBinding
import com.adematici.covidturkey.model.CovidModel
import com.adematici.covidturkey.service.CovidAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VakaDetayFragment : Fragment() {

    private lateinit var binding: FragmentVakaDetayBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        loadData()
        binding = FragmentVakaDetayBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CovidAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CovidModel>> {
            override fun onResponse(
                call: Call<List<CovidModel>>,
                response: Response<List<CovidModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        binding.textViewZaturreOrani.text = it[0].zaturreorani
                        binding.textViewYatakDolulukOrani.text = it[0].yatakdolulukorani
                        binding.textViewEriskinYogunBakimDolulugu.text = it[0].eriskinyogunbakimdolulukorani
                        binding.textViewVentilatorDoluluk.text = it[0].ventilatordolulukorani
                        binding.textViewToplamTestSayisi.text = it[0].toplamtestsayisi
                        binding.textViewToplamVakaSayisi.text = it[0].toplamvakasayisi
                        binding.textViewToplamVefatSayisi.text = it[0].toplamvefatsayisi
                        binding.textViewToplamAgirHastaSayisi.text = it[0].toplamagirhastasayisi
                        binding.textViewToplamIyilesenSayisi.text = it[0].toplamiyilesensayisi
                    }
                }
            }

            override fun onFailure(call: Call<List<CovidModel>>, t: Throwable) {
                Toast.makeText(activity,"İnternet Bağlantınızı Konrtol Edin", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }

}