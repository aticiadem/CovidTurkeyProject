package com.adematici.covidturkey.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adematici.covidturkey.databinding.FragmentBelirtiBinding

class BelirtiFragment : Fragment() {

    lateinit var binding: FragmentBelirtiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBelirtiBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}