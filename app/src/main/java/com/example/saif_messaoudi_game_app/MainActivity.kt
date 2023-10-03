package com.example.saif_messaoudi_game_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saif_messaoudi_game_app.databinding.ActivityLoginBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

