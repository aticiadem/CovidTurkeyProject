package com.adematici.covidturkey.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.adematici.covidturkey.R
import com.adematici.covidturkey.databinding.FragmentIstatistikBinding

class IstatistikFragment : Fragment() {

    lateinit var binding: FragmentIstatistikBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIstatistikBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonVakaDetaylari.setOnClickListener {
            val action = IstatistikFragmentDirections.actionİstatistikFragmentToVakaDetayFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}