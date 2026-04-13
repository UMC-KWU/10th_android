package com.neouul.umc10android.week04.core

import androidx.fragment.app.Fragment

fun Fragment.showLoading() {
    val existing = parentFragmentManager.findFragmentByTag("LoadingDialog")
    if (existing == null) {
        LoadingDialog().show(parentFragmentManager, "LoadingDialog")
    }
}

fun Fragment.hideLoading() {
    val existing = parentFragmentManager.findFragmentByTag("LoadingDialog")
    if (existing is LoadingDialog) {
        existing.dismiss()
    }
}
