package com.example.agencedrive.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agencedrive.adapter.MyAdapter
import com.example.agencedrive.adapter.News
import com.example.agencedrive.R
import com.example.agencedrive.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var newRecylerView: RecyclerView
    private lateinit var newArrayList: ArrayList<News>
    lateinit var imageId: ArrayList<Int>
    lateinit var heading: ArrayList<String>
    lateinit var hours: ArrayList<String>
    lateinit var searchView: SearchView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        Log.i("{}message","home")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initiazeView(root)
        return root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initiazeView(root: View) {
        Log.i("{}message","home")

//        searchView = requireView().findViewById(R.id.searchview)
//        searchView.clearFocus()
//        searchView.setOnQueryTextListener(/* listener = */ object )
        newRecylerView = root.findViewById(R.id.recycleview)
        val list: List<News> = listOf(

            News(titleImag = "", heading = "firstgdcbjbiijdhjnbnbjbjjhhuhxsxshxshixshihsic"),
            News(titleImag = "", heading = "secondcbsbcjkshskldllsJlkjididhhduidhushcsjcsjsc"),
            News(titleImag = "", heading = "thirdhbscbjsbclsansnduidgeuyiwywiwiwidwydjscjskbshod"),
            News(titleImag = "", heading = "fourthbdiuduolidloidojwiwohwdhsdhudiwhdwidj;di;djiudwoduqhdihdu")
        )
//            Log.i("","$newRecylerView")
        Log.i("{}message","recycleview")
        var adapter = MyAdapter(list, requireContext())
        newRecylerView.layoutManager = LinearLayoutManager(requireContext())
        newRecylerView.adapter = adapter
//        newRecylerView.setHasFixedSize(true)
//        newArrayList = arrayListOf<News>()
    }
}