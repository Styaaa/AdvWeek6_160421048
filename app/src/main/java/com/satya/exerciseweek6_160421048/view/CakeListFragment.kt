package com.satya.exerciseweek6_160421048.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.satya.exerciseweek6_160421048.databinding.FragmentCakeListBinding
import com.satya.exerciseweek6_160421048.viewmodel.CakeViewModel


class CakeListFragment : Fragment() {
    private lateinit var viewModel: CakeViewModel
    private val cakeListAdapter = CakeListAdapter(arrayListOf())
    private lateinit var binding: FragmentCakeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CakeViewModel::class.java)
        viewModel.refresh()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = cakeListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.cakesLD.observe(viewLifecycleOwner, Observer {
            cakeListAdapter.updateCakeList(it)
        })

//        viewModel.cakeLoadErrorLD.observe(viewLifecycleOwner, Observer {
//
//        })
    }
}