package com.example.fampayassgnt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fampayassgnt.network.helper.Resource
import com.example.fampayassgnt.dataModels.CardGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FamPayViewModel @Inject constructor(private val famPayApiRepository: FamPayApiRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(GetScreenState())
    val uiState: StateFlow<GetScreenState> = _uiState

    private val _uiData = MutableStateFlow(ApiData())
    val uiData : StateFlow<ApiData> = _uiData

    fun getFamApiResponse() = viewModelScope.launch(Dispatchers.IO) {
        _uiState.emit(uiState.value.copy(isLoading = true)) //, showNoInternetDialog = false, showToast = ""))
        val apiResultData = famPayApiRepository.getFamPayApiResponse()

        if(apiResultData is Resource.Success && apiResultData.data != null){
            _uiState.emit(uiState.value.copy(isLoading = false, showToast = apiResultData.message))
            _uiData.emit(uiData.value.copy(apiResultData.data!!.cardGroups))

        }else {
            if(apiResultData is Resource.Error)
            _uiState.emit(uiState.value.copy(isLoading = false, showToast = apiResultData.message))
        }
    }
}

data class GetScreenState(
    val isLoading: Boolean = false,
    val showNoInternetDialog : Boolean = false,
    val showToast : String = ""
)

data class ApiData(
    val data : List<CardGroup>? = null
)
