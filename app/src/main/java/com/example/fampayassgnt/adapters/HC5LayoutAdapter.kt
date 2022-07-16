package com.example.fampayassgnt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fampayassgnt.Objects
import com.example.fampayassgnt.Objects.loadImage
import com.example.fampayassgnt.databinding.Hc5CardItemLayoutBinding
import com.example.fampayassgnt.dataModels.Card

class HC5LayoutAdapter (val onActionBtnClicked: (Card) -> Unit)
                        : ListAdapter<Card, HC5LayoutAdapter.ViewHolder>(Objects.DiffCall()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            Hc5CardItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onActionBtnClicked)
    }

    inner class ViewHolder(private var binding: Hc5CardItemLayoutBinding) :

        RecyclerView.ViewHolder(binding.root){

        fun bind(singleItem: Card?, onActionBtnClicked: (Card) -> Unit){

         //binding.hc5Name.text = singleItem.name
         singleItem?.bgImage?.imageUrl?.let { loadImage(it, binding.hc5CardIv) }
         //binding.hc5Card.setBackgroundColor(Color.parseColor(singleItem.bgColor))
         binding.hc5Card.setOnClickListener {
             if (singleItem != null) {
                 onActionBtnClicked(singleItem)
             }
         }

        }
    }

}

