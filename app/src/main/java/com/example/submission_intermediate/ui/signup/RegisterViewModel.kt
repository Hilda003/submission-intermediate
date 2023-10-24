package com.example.submission_intermediate.ui.signup

import androidx.lifecycle.ViewModel
import com.example.submission_intermediate.data.Repository

class RegisterViewModel(private val storyRepo: Repository) : ViewModel() {
    fun register(name: String, email: String, pw: String) =
        storyRepo.register(name, email, pw)
}