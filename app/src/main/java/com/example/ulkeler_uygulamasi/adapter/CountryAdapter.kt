package com.example.ulkeler_uygulamasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.ulkeler_uygulamasi.R
import com.example.ulkeler_uygulamasi.databinding.ItemCountrylistBinding
import com.example.ulkeler_uygulamasi.model.Country
import com.example.ulkeler_uygulamasi.util.downloadFromUrl
import com.example.ulkeler_uygulamasi.util.placeholderProgressBar
import com.example.ulkeler_uygulamasi.view.ListFragmentDirections
import kotlinx.android.synthetic.main.fragment_country.view.*
import kotlinx.android.synthetic.main.item_countrylist.view.*

class CountryAdapter(val countryList : ArrayList<Country>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener{


    class CountryViewHolder(val view :ItemCountrylistBinding):RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val view = DataBindingUtil.inflate<ItemCountrylistBinding>(inflater,R.layout.item_countrylist,parent,false)
      return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this



        /*
        holder.view.name_Text.text = countryList[position].countryName
        holder.view.ragion_Text.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.country_imageView.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.view.context)   )
    */

    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()

    }

    override fun onCountryClicked(v: View) {
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = ListFragmentDirections.actionListFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)

    }

}