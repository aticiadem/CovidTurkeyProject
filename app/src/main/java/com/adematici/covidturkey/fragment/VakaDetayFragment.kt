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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class VakaDetayFragment : Fragment() {

    private lateinit var binding: FragmentVakaDetayBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var compositeDisposable: CompositeDisposable
    private var model: ArrayList<CovidModel>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        compositeDisposable = CompositeDisposable()
        loadData()
        binding = FragmentVakaDetayBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CovidAPI::class.java)

        compositeDisposable.add(retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))

    }

    private fun handleResponse(veriListesi: List<CovidModel>){
        model = ArrayList(veriListesi)
        model?.let {
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

}