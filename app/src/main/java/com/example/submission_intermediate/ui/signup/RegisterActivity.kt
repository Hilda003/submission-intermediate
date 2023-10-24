package com.example.submission_intermediate.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.submission_intermediate.MainActivity
import com.example.submission_intermediate.ViewModelFactory
import com.example.submission_intermediate.data.Result
import com.example.submission_intermediate.databinding.ActivityRegisterBinding
import com.example.submission_intermediate.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val factory : ViewModelFactory = ViewModelFactory.getInstance(this)
        val registerViewModel : RegisterViewModel by viewModels{
            factory
        }

        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            registerViewModel.register(
                binding.editName.text.toString(),
                binding.EmailText.text.toString(),
                binding.PasswordText.text.toString()
            ).observe(
                this,
            ) {
                when(it) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = android.view.View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = android.view.View.GONE
                        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = android.view.View.GONE
                        Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
        supportActionBar?.hide()
    }
}