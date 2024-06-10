package com.example.biblioteca.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.biblioteca.models.Generos
import com.example.biblioteca.repositories.GeneroRepository

class MainViewModel:ViewModel() {
    private val _generoList: MutableLiveData<Generos> by lazy{
        MutableLiveData<Generos>(Generos())
    }
    val generoList: LiveData<Generos> get() = _generoList

    fun fetchListaLibros(){
        GeneroRepository.getGeneroList(
            success = {generos ->
                      generos?.let {
                          Log.d("MainViewModel", "Fetched generos: $it")
                          _generoList.value = it
                      }
            },
            failure = {
                it.printStackTrace()

            }
        )
    }
}