package com.ithub.lesson2

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ithub.lesson2.databinding.DiaFragmentBinding

class DiaFragment : DialogFragment(){

    private lateinit var binding: DiaFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DiaFragmentBinding.inflate(inflater, container, false)
        binding.next.setOnClickListener {
            dismissAllowingStateLoss()
        }
        binding.close.setOnClickListener {
            dismissAllowingStateLoss()
        }
        return binding.root
    }

}