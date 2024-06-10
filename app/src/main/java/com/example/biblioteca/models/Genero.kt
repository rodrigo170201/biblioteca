package com.example.biblioteca.models

typealias Generos = ArrayList<Genero>

data class Genero(
    var nombre: String
){
    var id: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null
}
