package com.example.fampayassgnt.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fampayassgnt.Objects
import com.example.fampayassgnt.Objects.loadImage
import com.example.fampayassgnt.databinding.Hc3CardItemLayoutBinding
import com.example.fampayassgnt.dataModels.Card

class HC3LayoutAdapter (val onActionBtnClicked: (Card) -> Unit,
                        val onRemindLaterClick: (Int) -> Unit): ListAdapter<Card, HC3LayoutAdapter.ViewHolder>(
    Objects.DiffCall()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            Hc3CardItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onActionBtnClicked, onRemindLaterClick, position)
    }

    inner class ViewHolder(private var binding: Hc3CardItemLayoutBinding) :

        RecyclerView.ViewHolder(binding.root){

        fun bind(singleItem: Card?,
                 onActionBtnClicked: (Card) -> Unit,
                 onRemindLaterClick: (Int) -> Unit,
                 position: Int){

         binding.hc3Name.text = singleItem?.name?.toString()
         binding.h3cTitle.text =  singleItem?.title?.toString()

         binding.hc3Description.text = singleItem?.description?.toString()
         binding.h3cCardLayout.setBackgroundColor(Color.parseColor(singleItem?.bgColor))

         binding.h3cActionBtn.apply {
             this.text = singleItem?.cta?.get(0)?.text
             this.setBackgroundColor(Color.parseColor(singleItem?.cta?.get(0)?.bgColor))
             this.setTextColor(Color.parseColor(singleItem?.cta?.get(0)?.textColor))
             this.setOnClickListener {
                 if (singleItem != null) {
                     onActionBtnClicked(singleItem)
                 }
             }
         }
         binding.h3cCard.setOnLongClickListener {
             //onLongClick(it, binding)
             if(binding.llExtra.visibility == View.VISIBLE){
                 binding.llExtra.visibility = View.GONE

             }else{
                 println("mar + ${binding.h3cCard.marginLeft}")
                 binding.llExtra.visibility = View.VISIBLE
                 (binding.h3cCard.layoutParams as? ViewGroup.MarginLayoutParams)?.leftMargin = 320
             }
             true
         }

         binding.remindLaterCv.setOnClickListener {
             onRemindLaterClick(position)
         }

            binding.dismissNowCv.setOnClickListener {
                onRemindLaterClick(position)
            }

            singleItem?.bgImage?.imageUrl?.let { loadImage(it, binding.h3cCardIv) }

        }

    }

}

