package com.example.saif_messaoudi_game_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.saif_messaoudi_game_app.databinding.ValidationNumberBinding
import com.google.android.material.snackbar.Snackbar


class ValidationNumber : AppCompatActivity()  {

    private lateinit var binding : ValidationNumberBinding

    var code : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ValidationNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        code = intent.getStringExtra("CODE").toString()

        initBackArrowToolBar()

        initOnClicks()

    }

    // Tool Bar Back Arrow START *************************************
    fun initBackArrowToolBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //use back arrow from drawable
        val upArrow = resources.getDrawable(R.drawable.ic_back_arrow)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId()==android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    // Tool Bar Back Arrow END ***************************************

    private fun initOnClicks() {
        binding.btnVerify.setOnClickListener {
            var match = matchingCode()
            if (match) {
                val intent = Intent(this, NewPassword::class.java)
                startActivity(intent)
            } else {
                val snackError = Snackbar.make(binding.contextView,"You have some errors in your inputs!", Snackbar.LENGTH_LONG)
                    .setTextColor(ContextCompat.getColor(this, R.color.white))
                snackError.show()
            }

        }
        binding.resenCode.setOnClickListener {
            val snackError = Snackbar.make(binding.contextView,"Coming soon :)", Snackbar.LENGTH_LONG)
                .setTextColor(ContextCompat.getColor(this, R.color.white))
            snackError.show()
        }
    }

    private fun matchingCode() : Boolean {
        var digit1 = binding.number1.text.toString()
        var digit2 = binding.number2.text.toString()
        var digit3 = binding.number3.text.toString()
        var digit4 = binding.number4.text.toString()

        if (!digit1.equals(code[0].toString()))
            return false
        if (!digit2.equals(code[1].toString()))
            return false
        if (!digit3.equals(code[2].toString()))
            return false
        if (!digit4.equals(code[3].toString()))
            return false

        return true

    }
}