package com.example.biblioteca.repositories

import android.util.Log
import com.example.biblioteca.api.APILibrosService
import com.example.biblioteca.models.Genero
import com.example.biblioteca.models.Generos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GeneroRepository {
    fun getGeneroList(success:(Generos?) -> Unit, failure:(Throwable)-> Unit){
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService=
            retrofit.create(APILibrosService::class.java)
        service.getGeneros().enqueue(object : Callback<Generos>{
            override fun onResponse(res: Call<Generos>, response: Response<Generos>) {
                if (response.isSuccessful) {
                    val postList = response.body()
                    Log.d("GeneroRepository", "Response successful: $postList")
                    success(postList)
                } else {
                    Log.e("GeneroRepository", "Response not successful")
                    failure(Throwable("Response not successful"))
                }
            }

            override fun onFailure(res: Call<Generos>, t: Throwable) {
                Log.e("GeneroRepository", "Failed to fetch data", t)

                failure(t)
            }
        })
    }
    fun insertGenero(
        category: Genero,
        success: (Genero) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.insertGenero(category).enqueue(object : Callback<Genero> {
            override fun onResponse(res: Call<Genero>, response: Response<Genero>) {
                if (response.isSuccessful) {
                    val objGenero = response.body()
                    if (objGenero != null) {
                        success(objGenero!!)
                    } else {
                        val error = "Response body is null"
                        Log.e("GeneroInsertRepository", error)
                        failure(Throwable(error))
                    }
                } else {
                    val error = "Response not successful: ${response.errorBody()?.string()}"
                    Log.e("GeneroRepository", error)
                    failure(Throwable(error))
                }
            }

            override fun onFailure(res: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun getGenero(id: Int, success: (Genero?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.getGeneroById(id).enqueue(object : Callback<Genero?> {
            override fun onResponse(res: Call<Genero?>, response: Response<Genero?>) {
                success(response.body())
            }

            override fun onFailure(res: Call<Genero?>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun updateGenero(
        category: Genero,
        success: (Genero) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        val categoryId = category.id ?: 0
        Log.d("GeneroRepository", "Updating genero with id: $categoryId")
        service.updateGenero(category, categoryId).enqueue(object : Callback<Genero> {
            override fun onResponse(res: Call<Genero>, response: Response<Genero>) {
                if (response.isSuccessful) {
                    val objGenero = response.body()
                    if (objGenero != null) {
                        success(objGenero)
                    } else {
                        val error = "Response body is null"
                        Log.e("GeneroRepository", error)
                        failure(Throwable(error))
                    }
                } else {
                    val error = "Response not successful: ${response.errorBody()?.string()}"
                    Log.e("GeneroRepository", error)
                    failure(Throwable(error))
                }
            }

            override fun onFailure(res: Call<Genero>, t: Throwable) {
                Log.e("GeneroRepository", "Failed to update genero", t)

                failure(t)
            }
        })
    }


    fun deleteGenero(
        id: Int,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibrosService =
            retrofit.create(APILibrosService::class.java)
        service.deleteGenero(id).enqueue(object : Callback<Void> {
            override fun onResponse(res: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(res: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }

}