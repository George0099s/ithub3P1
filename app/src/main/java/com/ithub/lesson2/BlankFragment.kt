package com.ithub.lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment


class BlankFragment : Fragment() {

    private lateinit var nameED: EditText
    private lateinit var next: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_blank, container, false)
        nameED = v.findViewById(R.id.name_ed)
        next = v.findViewById(R.id.next)

        next.apply {
            setOnClickListener {
//                val fragment = OrderFragment()
//                val bundle = Bundle()
//                bundle.putString("name", nameED.text.toString())
//                fragment.arguments = bundle

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("order")
                    .replace(R.id.inner_cont, OrderFragment().apply {
                        arguments = Bundle().apply {
                            putString("name", nameED.text.toString())
                        }
                    }
                    )
                    .commit()
            }
            text = "далее"
            isEnabled = true
        }


        return v
    }
}