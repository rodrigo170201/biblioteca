package com.example.biblioteca.api

import com.example.biblioteca.models.Genero
import com.example.biblioteca.models.Generos
import com.example.biblioteca.models.Libro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APILibrosService {
    @GET("generos")
    fun getGeneros(): Call<Generos>






    @GET("generos/{id}")
    fun getGeneroById(
        @Path("id") id: Int
    ): Call<Genero?>


    @POST("generos")
    fun insertGenero(
        @Body genero: Genero
    ): Call<Genero>

    @PUT("generos/{id}")
    fun updateGenero(
        @Body genero: Genero,
        @Path("id") id: Int
    ): Call<Genero>


    @DELETE("generos/{id}")
    fun deleteGenero(
        @Path("id") id: Int
    ): Call<Void>

    @GET ("libros")
    fun obtenerTodosLosLibros():Call<List<Libro>>

}