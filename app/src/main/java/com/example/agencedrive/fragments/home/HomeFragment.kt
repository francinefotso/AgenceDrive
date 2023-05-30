package com.example.agencedrive.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agencedrive.MyAdapter
import com.example.agencedrive.News
import com.example.agencedrive.R
import com.example.agencedrive.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var newRecylerView: RecyclerView
    private lateinit var newArrayList: ArrayList<News>
    lateinit var imageId: ArrayList<Int>
    lateinit var heading: ArrayList<String>
    lateinit var hours: ArrayList<String>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
        initiazeView(root)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initiazeView(root: View) {
        newRecylerView = root.findViewById(R.id.recycleview)
        val list: List<News> = listOf(

            News(titleImag = "", heading = "first", hours = "12:30 pm"),
            News(titleImag = "", heading = "second", hours = "14:30s pm"),
            News(titleImag = "", heading = "third", hours = "14:30s pm"),
            News(titleImag = "", heading = "fourth", hours = "14:30s pm")
        )
//            Log.i("","$newRecylerView")
        var adapter = MyAdapter(list, requireContext())
        newRecylerView.layoutManager = LinearLayoutManager(requireContext())
        newRecylerView.adapter = adapter
//        newRecylerView.setHasFixedSize(true)
//        newArrayList = arrayListOf<News>()
    }
}