package com.example.submission_intermediate.di

import android.content.Context
import com.example.submission_intermediate.retrofit.ApiConfig
import com.example.submission_intermediate.data.Repository




object Injection {
    fun repositoryProvide(context: Context) : Repository {
//        val db = StoryDB.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return Repository(apiService)
    }
}