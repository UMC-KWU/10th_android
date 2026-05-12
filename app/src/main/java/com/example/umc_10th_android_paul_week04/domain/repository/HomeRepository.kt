package com.example.umc_10th_android_paul_week04.domain.repository

import com.example.umc_10th_android_paul_week04.data.model.HomeProduct

interface HomeRepository {
    fun getHomeProducts(): List<HomeProduct>
}