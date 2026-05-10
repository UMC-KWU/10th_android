package com.example.a10th_umc_week02.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10th_umc_week02.R.drawable.ic_blackshoes
import com.example.a10th_umc_week02.R.drawable.ic_whiteshoes
import com.example.a10th_umc_week02.data.model.HomeData
import com.example.a10th_umc_week02.di.DataStoreModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreModule: DataStoreModule
) : ViewModel() {
    private val _homeList = MutableStateFlow<List<HomeData>>(emptyList())
    val homeList: StateFlow<List<HomeData>> = _homeList

    init{
        loadHomeData()
    }

    fun loadHomeData(){
        val initialList = mutableListOf(
            HomeData("Air Jordan XXXVI", "US$185", ic_blackshoes),
            HomeData("Nike Air Force 1 '07", "US$115", ic_whiteshoes)
        )
        _homeList.value = initialList
        }
    }