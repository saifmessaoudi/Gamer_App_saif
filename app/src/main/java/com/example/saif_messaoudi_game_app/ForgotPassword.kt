package com.example.saif_messaoudi_game_app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.saif_messaoudi_game_app.databinding.ForgotPasswordBinding
import com.google.android.material.snackbar.Snackbar


class ForgotPassword : AppCompatActivity() {

    private lateinit var binding : ForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBackArrowToolBar()

        setInputsEmptyErrors()

        initSubmitClick()
        initSmsClick()

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

    // Empty Inputs Errors START *************************************
    private fun setInputsEmptyErrors() {
        setEmailInputEmptyError()
    }
    private fun setEmailInputEmptyError() {
        binding.emailTxt.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                if (binding.emailTxt.text.toString().isEmpty()) {
                    setEmailError()
                } else {
                    binding.EmailAddress.error = null
                }
            }
        }
    }
    private fun setEmailError() {
        binding.EmailAddress.error = getString(R.string.not_empty)
    }
    // Empty Inputs Errors END ***************************************

    // Buttons On Clicks START ***************************************
    private fun initSubmitClick() {
        binding.btnSubmit.setOnClickListener {
            if (binding.emailTxt.text.toString().isEmpty()) {
                setEmailError()
                erreurSnack()
            } else {
                val intent = Intent(this, ValidationNumber::class.java)
                    .putExtra("CODE", "0000")
                startActivity(intent)
            }
        }
    }
    private fun initSmsClick() {
        binding.btnSendSms.setOnClickListener {
            if (binding.emailTxt.text.toString().isEmpty()) {
                setEmailError()
                erreurSnack()
            } else {
                val intent = Intent(this, ValidationNumber::class.java)
                    .putExtra("CODE", "1111")
                startActivity(intent)
            }
        }
    }
    private fun erreurSnack() {
        val snackError = Snackbar.make(binding.contextView,"You have some errors in your inputs!", Snackbar.LENGTH_LONG)
            .setTextColor(ContextCompat.getColor(this, R.color.white))
        snackError.show()
    }
    // Buttons On Clicks END *****************************************

}