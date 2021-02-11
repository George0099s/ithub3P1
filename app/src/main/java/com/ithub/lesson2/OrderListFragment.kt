package com.ithub.lesson2

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ithub.lesson2.databinding.OrderListLayoutBinding


//fun <T: Any?>

data class Person(var name: String = "Name", var age: Int, val pos: String = "Teacher") : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}

class OrderListFragment : Fragment(), PersonClickListener {

    private lateinit var binding: OrderListLayoutBinding
    private lateinit var listAdapter: ArrayAdapter<Int>
    private lateinit var rvAdapter: MyAdapter
    private val personList = mutableListOf<Person>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OrderListLayoutBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        val listInt = mutableListOf<Int>()

        for (i in 0..100) {
            listInt.add(i)
            personList.add(Person(age = i))
        }
        rvAdapter = MyAdapter(personList, this)

        listAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listInt)

//        binding.list.adapter = CustomArrayAdapter(requireContext(), personList)

        binding.initData.setOnClickListener {

//            personList.add(0, Person(name = "new name", age = 22))


            personList.removeAt(4)
            val diffUtil = MyDiffUtil(rvAdapter.list, personList)
            val productDiffResult = DiffUtil.calculateDiff(diffUtil)

            rvAdapter.update(personList)
            productDiffResult.dispatchUpdatesTo(rvAdapter)
        }

        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = rvAdapter
        }
    }

    class CustomArrayAdapter : ArrayAdapter<Person> {
        private var list = listOf<Person>()

        constructor(context: Context) : super(context, android.R.layout.simple_list_item_1)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val item = list[position]

            var view = convertView
            if (convertView == null) {
                view = LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_2, null)
            }

            (view!!.findViewById<View>(android.R.id.text1) as TextView).text = item.name
            (view.findViewById<View>(android.R.id.text2) as TextView).text = item.age.toString()

            return view
        }

        constructor(context: Context, list: List<Person>) : super(
            context,
            android.R.layout.simple_list_item_1,
            list
        ) {
            this.list = list
        }
    }

    override fun onPersonClick(item: Person) {

//        requireActivity().supportFragmentManager
//            .beginTransaction()
//            .addToBackStack("ss")
//            .replace(R.id.frag_cont, DetailPerson().apply {
//                arguments = Bundle().apply {
//                    putParcelable("item", item)
//                }
//            }, "detailPerson")
//            .commit()
        Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
    }

}