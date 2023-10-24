package com.example.submission_intermediate.ui.login

import androidx.lifecycle.ViewModel
import com.example.submission_intermediate.data.Repository

class LoginViewModel(private val storyRepo: Repository): ViewModel() {
    fun postLogin(email: String, pass: String) = storyRepo.login(email, pass)
}