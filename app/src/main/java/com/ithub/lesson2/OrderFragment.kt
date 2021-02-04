package com.ithub.lesson2

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.ithub.lesson2.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private var count = 0
    private lateinit var name: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater, container, false)

        savedInstanceState?.let {
            count = it.getInt("count")
            name = it.getString("saved_name") ?: "no data"
        }


        initViews()

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putInt("count", count)
            putString("saved_name", name)
        }
        super.onSaveInstanceState(outState)
    }

    private fun initViews() {
        name = arguments?.getString("name") ?: "No data"
        binding.count.text = count.toString()
        binding.nameText.text = name

        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        binding.next.startAnimation(anim)


        binding.plus.setOnClickListener {
            count++
            binding.count.text = count.toString()
        }

        binding.minus.setOnClickListener {
            count--
            binding.count.text = count.toString()
        }

        binding.next.setOnClickListener {
//            val frag = DiaFragment()
//            frag.show(childFragmentManager, frag.tag)


            val adb = AlertDialog.Builder(requireContext(), R.style.DialogStyleAnim)
            adb.setTitle("Заказ верный?")
                .setPositiveButton(
                    "Да"
                ) { dialog, which ->
                    binding.orderTv.text =
                        "Заказчик: $name\nКофе: $count шт\nВаниль: ${if (binding.van.isChecked) "есть" else "нет"}\n" +
                                "Шоколад: ${if (binding.chock.isChecked) "есть" else "нет"} "
                    dialog.cancel()
                }
                .setNegativeButton("Нет") { dia, _ -> dia.cancel() }
                .setCancelable(false)
                .show()
        }
    }
}