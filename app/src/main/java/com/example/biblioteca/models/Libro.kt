package com.example.biblioteca.models

typealias Libros = ArrayList<Libro>

data class Libro(
    var nombre: String,
    var autor: String,
    var editorial: String,
    var imagen: String,
    var sinopsis: String,
    var isbn: String
) {
    var id: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null
}
