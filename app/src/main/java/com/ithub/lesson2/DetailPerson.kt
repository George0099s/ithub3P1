package com.ithub.lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ithub.lesson2.databinding.DetailPersonFragmnetBinding

class DetailPerson : Fragment() {

    private lateinit var binding: DetailPersonFragmnetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailPersonFragmnetBinding.inflate(inflater, container, false)

        val person = arguments?.getParcelable("item") as? Person
        binding.age.text = person?.age.toString()
        binding.name.text = person?.name

        return binding.root

    }
}