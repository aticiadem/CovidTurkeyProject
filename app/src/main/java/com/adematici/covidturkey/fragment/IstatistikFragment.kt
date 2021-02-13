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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class IstatistikFragment : Fragment() {

    lateinit var binding: FragmentIstatistikBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var compositeDisposable: CompositeDisposable? = null
    private var model: ArrayList<CovidModel>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIstatistikBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable = CompositeDisposable()

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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CovidAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))
    }

    private fun handleResponse(veriListesi: List<CovidModel>){
        model = ArrayList(veriListesi)
        model?.let {
            binding.textViewTarih.text = it[0].tarih
            binding.textViewTestSayisi.text = it[0].testsayisi
            binding.textViewVakaSayisi.text = it[0].vakasayisi
            binding.textViewHastaSayisi.text = it[0].hastasayisi
            binding.textViewVefatSayisi.text = it[0].vefatsayisi
            binding.textViewIyilesenSayisi.text = it[0].iyilesensayisi
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

}
