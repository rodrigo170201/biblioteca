package com.example.biblioteca.repositories

import android.util.Log
import com.example.biblioteca.api.APILibrosService
import com.example.biblioteca.models.Libro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LibroRepository {
    private val TAG = LibroRepository::class.java.simpleName

    fun obtenerTodosLosLibros(
        success: (List<Libro>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.obtenerTodosLosLibros().enqueue(object : Callback<List<Libro>> {
            override fun onResponse(call: Call<List<Libro>>, response: Response<List<Libro>>) {
                if (response.isSuccessful) {
                    val libros = response.body()
                    success(libros ?: emptyList())
                } else {
                    Log.e(TAG, "Response not successful")
                    failure(Throwable("Response not successful"))
                }
            }

            override fun onFailure(call: Call<List<Libro>>, t: Throwable) {
                Log.e(TAG, "Failed to fetch data", t)
                failure(t)
            }
        })
    }
}