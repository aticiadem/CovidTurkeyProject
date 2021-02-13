package com.adematici.covidturkey.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.adematici.covidturkey.R
import com.adematici.covidturkey.databinding.FragmentIstatistikBinding
import com.adematici.covidturkey.model.CovidModel
import com.adematici.covidturkey.service.CovidAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IstatistikFragment : Fragment() {

    lateinit var binding: FragmentIstatistikBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIstatistikBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        binding.buttonVakaDetaylari.setOnClickListener {
            val action = IstatistikFragmentDirections.actionİstatistikFragmentToVakaDetayFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CovidAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CovidModel>>{
            override fun onResponse(
                call: Call<List<CovidModel>>,
                response: Response<List<CovidModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        binding.textViewTarih.text = it[0].tarih
                        binding.textViewTestSayisi.text = it[0].testsayisi
                        binding.textViewVakaSayisi.text = it[0].vakasayisi
                        binding.textViewHastaSayisi.text = it[0].hastasayisi
                        binding.textViewVefatSayisi.text = it[0].vefatsayisi
                        binding.textViewIyilesenSayisi.text = it[0].iyilesensayisi
                    }
                }
            }

            override fun onFailure(call: Call<List<CovidModel>>, t: Throwable) {
                Toast.makeText(activity,"İnternet Bağlantınızı Konrtol Edin",Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }

}