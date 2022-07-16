package com.example.fampayassgnt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fampayassgnt.Objects
import com.example.fampayassgnt.Objects.loadImage
import com.example.fampayassgnt.databinding.Hc6ArrowItemLayoutBinding
import com.example.fampayassgnt.dataModels.Card

class HC6LayoutAdapter (val onActionBtnClicked: (Card) -> Unit)
                        : ListAdapter<Card, HC6LayoutAdapter.ViewHolder>(Objects.DiffCall()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            Hc6ArrowItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onActionBtnClicked)
    }

    inner class ViewHolder(private var binding: Hc6ArrowItemLayoutBinding) :

        RecyclerView.ViewHolder(binding.root){

        fun bind(singleItem: Card?, onActionBtnClicked: (Card) -> Unit){
         binding.hc6CardWithArrowTv.text = singleItem?.title

         singleItem?.icon?.imageUrl?.let { loadImage(it, binding.hc6CardIv) }

         binding.smallArrowCard.setOnClickListener {
             if (singleItem != null) {
                 onActionBtnClicked(singleItem)
             }
         }

        }

    }

}

