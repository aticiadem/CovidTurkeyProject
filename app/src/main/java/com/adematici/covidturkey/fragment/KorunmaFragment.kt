package com.adematici.covidturkey.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adematici.covidturkey.R
import com.adematici.covidturkey.databinding.FragmentKorunmaBinding

class KorunmaFragment : Fragment() {

    lateinit var binding: FragmentKorunmaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKorunmaBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}