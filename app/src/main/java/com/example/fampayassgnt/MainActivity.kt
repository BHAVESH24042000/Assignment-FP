package com.example.fampayassgnt

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fampayassgnt.Objects.makeStatusBarTransparent
import com.example.fampayassgnt.adapters.*
import com.example.fampayassgnt.databinding.ActivityMainBinding
import com.example.fampayassgnt.databinding.ContainerLayoutBinding
import com.example.fampayassgnt.dataModels.Card
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ContainerLayoutBinding
    private val viewModel: FamPayViewModel by viewModels()
    private var HC3LayoutAdapter : HC3LayoutAdapter? = null
    private var hc6LayoutAdapter : HC6LayoutAdapter? = null
    private var hc5LayoutAdapter : HC5LayoutAdapter? = null
    private var hc9LayoutAdapter : HC9LayoutAdapter? = null
    private var hc1LayoutAdapter : HC1LayoutAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = binding.includedLayout
        setContentView(binding.root)
        makeStatusBarTransparent()
        viewModel.getFamApiResponse()
        setupRefreshLayout()
        lifecycleScope.launchWhenStarted {
            viewModel.uiData.collectLatest {
                if(it.data != null) {
                    val h3cList =  mutableListOf<Card>()
                    val hc6List =  mutableListOf<Card>()
                    val hc5List =  mutableListOf<Card>()
                    val hc9List =  mutableListOf<Card>()
                    val hc1List =  mutableListOf<Card>()

                    for (item in it.data) {
                        if (item.designType == "HC3") {
                            item.cards?.let { it1 -> h3cList.addAll(it1) }
                        }else if( item.designType == "HC6"){
                            item.cards?.let { it1 -> hc6List.addAll(it1) }
                        }else if(item.designType == "HC5"){
                            item.cards?.let { it1 -> hc5List.addAll(it1) }
                        }else if(item.designType == "HC9"){
                            item.cards?.let { it1 -> hc9List.addAll(it1) }
                        }else if(item.designType == "HC1"){
                            item.cards?.let { it1 -> hc1List.addAll(it1) }
                        }
                    }
                    setupH3cRecyclerViewAdapter(h3cList)
                    setupHc6RecyclerViewAdapter(hc6List)
                    setupHc5RecyclerViewAdapter(hc5List)
                    setupHc9RecyclerViewAdapter(hc9List)
                    setupHc1RecyclerViewAdapter(hc1List)
                }

            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest {

                if(it.isLoading == true){
                    binding.loadingLayout.isVisible = true
                    binding2.cardsLayout.isVisible = false
                }

                if(it.isLoading == false){
                    binding.loadingLayout.isVisible = false
                    binding2.cardsLayout.isVisible = true
                }

                if(it.showToast.length > 0){
                   showToastFunction(it.showToast)
                }
            }
        }
    }

    private fun showToastFunction(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun setupHc1RecyclerViewAdapter(list: MutableList<Card>) {
        binding2.hc1RecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hc1LayoutAdapter = HC1LayoutAdapter( {onActionButtonClick(it)})
        hc1LayoutAdapter!!.submitList(list)
        binding2.hc1RecyclerView.adapter = hc1LayoutAdapter
    }

    private fun setupHc9RecyclerViewAdapter(list: MutableList<Card>) {
        binding2.hc9RecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hc9LayoutAdapter = HC9LayoutAdapter( {onActionButtonClick(it)})
        hc9LayoutAdapter!!.submitList(list)
        binding2.hc9RecyclerView.adapter = hc9LayoutAdapter
    }


    private fun setupHc5RecyclerViewAdapter(list: MutableList<Card>) {
        binding2.hc5RecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hc5LayoutAdapter = HC5LayoutAdapter( {onActionButtonClick(it)})
        hc5LayoutAdapter!!.submitList(list)
        binding2.hc5RecyclerView.adapter = hc5LayoutAdapter

    }


    private fun setupHc6RecyclerViewAdapter(list: MutableList<Card>){
        //set the recycler view in horizontal way
        binding2.hc6RecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hc6LayoutAdapter = HC6LayoutAdapter( {onActionButtonClick(it)})
        hc6LayoutAdapter!!.submitList(list)
        binding2.hc6RecyclerView.adapter = hc6LayoutAdapter

    }


    private fun setupH3cRecyclerViewAdapter(list: MutableList<Card>){
        //set the recycler view in horizontal way
        binding2.hc3RecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        HC3LayoutAdapter = HC3LayoutAdapter( {onActionButtonClick(it)}, {removeItemH3cRecyclerViewAdapter(it)})
        HC3LayoutAdapter!!.submitList(list)
        binding2.hc3RecyclerView.adapter = HC3LayoutAdapter

    }

    private fun onActionButtonClick(card: Card) {
        val url = card.url
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
    private fun removeItemH3cRecyclerViewAdapter(position: Int){
        val currentList =  HC3LayoutAdapter?.currentList?.toMutableList()
        currentList?.removeAt(position)
        HC3LayoutAdapter?.submitList(currentList)
    }
    private fun setupRefreshLayout(){
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getFamApiResponse()
            binding.swipeRefresh.isRefreshing = false
        }
    }
}