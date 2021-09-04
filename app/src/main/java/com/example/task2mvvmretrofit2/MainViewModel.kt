package com.example.task2mvvmretrofit2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val movieList = MutableLiveData<List<Model>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {

        val response = repository.getAllMovies()
        response.enqueue(object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}