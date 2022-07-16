package com.example.fampayassgnt

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.fampayassgnt.dataModels.Card

object Objects {

    enum class ERROR_TYPE {
        NO_INTERNET, UNKNOWN
    }

    const val NO_INTERNET_MESSAGE = "Looks like you don't have an active internet connection"

    class DiffCall : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(
            oldItem: Card,
            newItem: Card
        ): Boolean {
            return oldItem.name + oldItem.title == newItem.name + oldItem.title
        }

        override fun areContentsTheSame(
            oldItem: Card,
            newItem: Card
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun loadImage( url : String, view : ImageView){
        Glide.with(view).load(url).into(view)
    }

    fun Activity.makeStatusBarTransparent() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = Color.TRANSPARENT
        }
    }

}