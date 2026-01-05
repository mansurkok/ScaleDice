package com.example.ulkeler_uygulamasi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulkeler_uygulamasi.view.ListFragmentDirections
import com.example.ulkeler_uygulamasi.R
import com.example.ulkeler_uygulamasi.adapter.CountryAdapter
import com.example.ulkeler_uygulamasi.model.Country
import com.example.ulkeler_uygulamasi.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel :ListViewModel
    private var countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refreshData()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = countryAdapter

       /* view.findViewById<Button>(R.id.toCountry_Button).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        } */

        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            countryErrorText.visibility = View.GONE
            countryLoadingBar.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                recyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer { countryError->
            countryError?.let {
                if(it){
                    countryErrorText.visibility = View.VISIBLE
                }else{
                    countryErrorText.visibility = View.GONE
                }
            }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { countryLoading->
            countryLoading?.let {
                if (it){
                    countryLoadingBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    countryErrorText.visibility = View.GONE
                }else {
                    countryLoadingBar.visibility = View.GONE
                }
            }
        })

    }


}