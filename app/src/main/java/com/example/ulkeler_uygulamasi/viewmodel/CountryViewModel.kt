package com.example.ulkeler_uygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ulkeler_uygulamasi.model.Country
import com.example.ulkeler_uygulamasi.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData =MutableLiveData<Country>()

    fun getDataFromRoom(uuid : Int) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country =  dao.getCountry(uuid)
            countryLiveData.value =country
        }

    }
}