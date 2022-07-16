package com.example.fampayassgnt.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fampayassgnt.Objects
import com.example.fampayassgnt.Objects.loadImage
import com.example.fampayassgnt.databinding.Hc1ArrowItemLayoutBinding
import com.example.fampayassgnt.dataModels.Card

class HC1LayoutAdapter (val onActionBtnClicked: (Card) -> Unit)
                        : ListAdapter<Card, HC1LayoutAdapter.ViewHolder>(Objects.DiffCall()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            Hc1ArrowItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onActionBtnClicked)
    }

    inner class ViewHolder(private var binding: Hc1ArrowItemLayoutBinding) :

        RecyclerView.ViewHolder(binding.root){

        fun bind(singleItem: Card?, onActionBtnClicked: (Card) -> Unit){

         binding.hc1CardTv1.text = singleItem?.name
         binding.hc1CardTitle.text = singleItem?.title

         singleItem?.bgColor?.let { binding.hc1Layout.setBackgroundColor(Color.parseColor(it)) }
         singleItem?.icon?.imageUrl?.let { loadImage(it, binding.hc1CardIv) }
         binding.hc1Layout.setOnClickListener {
             if (singleItem != null) {
                 onActionBtnClicked(singleItem)
             }
         }

        }
    }

}

