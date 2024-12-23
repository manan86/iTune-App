package com.codegalaxy.ituneapicall.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codegalaxy.ituneapicall.model.Artist
import com.codegalaxy.ituneapicall.model.TuneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TuneViewModel @Inject constructor(
    private val repository: TuneRepository
) : ViewModel(){
    private val _tune = MutableStateFlow<List<Artist>>(emptyList())
    val tune : StateFlow<List<Artist>> = _tune

    fun getDataViewModel(termViewModel : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchArtist(termViewModel)
                if (response.isSuccessful) {
                    response.body()?.results?.let { artistList ->
                        _tune.emit(artistList)
                        Log.d("data","${response.body()}")
                    } ?: println("Error: Empty response body")
                }
                else{
                    print("Error Data Not Found")
                }
            }
            catch (e : Exception){
                e.printStackTrace()
                println("Error : ${e.message}")
            }
        }
    }
}