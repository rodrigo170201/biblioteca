package com.example.biblioteca.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.biblioteca.models.Genero
import com.example.biblioteca.repositories.GeneroRepository

class GeneroDetailViewModel: ViewModel() {
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: LiveData<Boolean> get() = _closeActivity
    private val _genero: MutableLiveData<Genero?> by lazy {
        MutableLiveData<Genero?>(null)
    }
    val genero: LiveData<Genero?> get() = _genero

    fun saveGenero(nombre: String, id: Int) {
        Log.d("GeneroDetailViewModel", "Saving genero with name: $nombre and id: $id")
        val genero = Genero(
            nombre = nombre
        )
        if (id == -1) {
            GeneroRepository.insertGenero(genero,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        } else {
            genero.id = id
            GeneroRepository.updateGenero(genero,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
    }

    fun loadGenero(id: Int) {
        GeneroRepository.getGenero(id,
            success = {
                _genero.value = it
            },
            failure = {
                it.printStackTrace()
            })
    }
}