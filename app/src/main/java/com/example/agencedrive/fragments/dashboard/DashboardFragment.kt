package com.example.agencedrive.fragments.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.agencedrive.R
import com.example.agencedrive.databinding.FragmentDashboardBinding
import com.example.agencedrive.services.AuthentificationServiceActivity

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var dec: Button
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }
    private fun intialize() {
        dec = requireActivity().findViewById(R.id.decon)
//        nom = requireActivity().findViewById(R.id.namefran)
//        let = requireActivity().findViewById(R.id.letter)
    }
    fun deconnection() {
        dec.setOnClickListener {
            AuthentificationServiceActivity.logout(requireContext())
            Log.i("{logout}", "saluttt")

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intialize()
        deconnection()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}