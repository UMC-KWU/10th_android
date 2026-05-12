package com.example.umc_10th_android_paul_week04.data.repository

import com.example.umc_10th_android_paul_week04.R
import com.example.umc_10th_android_paul_week04.data.model.HomeProduct
import com.example.umc_10th_android_paul_week04.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override fun getHomeProducts(): List<HomeProduct> {
        return arrayListOf(
            HomeProduct(R.drawable.shoe_jordan_36, "Air Jordan XXXVI", "US$185"),
            HomeProduct(R.drawable.shoe_jordan_01, "Air Jordan 1 Mid", "US$125"),
            HomeProduct(R.drawable.shoe_airforce_01, "Nike Air Force 1 '07", "US$115")
        )
    }
}