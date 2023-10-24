package com.example.submission_intermediate.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.submission_intermediate.MainActivity
import com.example.submission_intermediate.ViewModelFactory
import com.example.submission_intermediate.data.Result
import com.example.submission_intermediate.data.UserPreferences
import com.example.submission_intermediate.data.Username
import com.example.submission_intermediate.databinding.ActivityLoginBinding
import com.example.submission_intermediate.ui.signup.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private var modelUser : Username = Username()
    private lateinit var userPreferences: UserPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModelFactory : ViewModelFactory = ViewModelFactory.getInstance(this)
        val loginViewModel : LoginViewModel by viewModels{
            viewModelFactory
        }

        val content = SpannableString("Signup")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.txtSignup.text = content


        binding.btnLogin.setOnClickListener {
            loginViewModel.postLogin(
                binding.EmailText.text.toString(),
                binding.PasswordText.text.toString()
            ).observe(this) {
                if (it != null) {
                    when (it) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Login ${it.data.message}" , Toast.LENGTH_SHORT).show()
                            val saveResponse = it.data
                            tokenSave(saveResponse.loginResult.token)
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra(EX, saveResponse.loginResult.token)
                            startActivity(intent)
                        }

                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Login ${it.error}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        binding.txtSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }



    }

    private fun tokenSave(token: String) {
        modelUser.tokenName = token
        userPreferences.setUserPreferences(modelUser)

    }


    companion object {
        const val EX = "EX"
    }


}