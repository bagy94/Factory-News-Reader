package hr.bagy94.factorynewsreader.utils

import androidx.fragment.app.FragmentActivity


fun FragmentActivity.setBackButtonInToolbarVisible(visible:Boolean){
    actionBar?.let {
        it.setDisplayShowHomeEnabled(true)
        it.setDisplayHomeAsUpEnabled(true)
    }
}