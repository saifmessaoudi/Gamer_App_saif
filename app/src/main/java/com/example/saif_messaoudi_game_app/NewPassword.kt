package com.example.saif_messaoudi_game_app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.saif_messaoudi_game_app.databinding.NewPasswordBinding
import com.google.android.material.snackbar.Snackbar


class NewPassword : AppCompatActivity() {

    private lateinit var binding : NewPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBackArrowToolBar()

        setInputsEmptyErrors()

        initSubmitBtn()

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
        setPasswordInputEmptyError()
        setConfirmPasswordInputEmptyError()
    }
    private fun setPasswordInputEmptyError() {
        binding.passwordTxt.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus)
                setPasswordError()
        }
    }
    private fun setPasswordError() {
        if (binding.passwordTxt.text.toString().isEmpty()) {
            binding.Password.error = getString(R.string.not_empty)
        }else if (binding.passwordTxt.text.toString() != binding.passwordConfirmTxt.text.toString()) {
            binding.Password.error = "Must be the same password!"
        } else {
            binding.Password.error = null
        }
    }
    private fun setConfirmPasswordInputEmptyError() {
        binding.passwordConfirmTxt.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus)
                setConfirmPasswordError()
        }
    }
    private fun setConfirmPasswordError() {
        if (binding.passwordConfirmTxt.text.toString().isEmpty()) {
            binding.ConfirmPassword.error = getString(R.string.not_empty)
        }else if (binding.passwordTxt.text.toString() != binding.passwordConfirmTxt.text.toString()) {
            binding.ConfirmPassword.error = "Must be the same password!"
        } else {
            binding.ConfirmPassword.error = null
        }
    }
    // Empty Inputs Errors END ***************************************

    private fun initSubmitBtn() {
        val snackError = Snackbar.make(binding.contextView,"You have some errors in your inputs!", Snackbar.LENGTH_LONG)
            .setTextColor(ContextCompat.getColor(this, R.color.white))

        binding.btnSubmit.setOnClickListener {
            if (binding.passwordTxt.text.toString().isNotEmpty() && binding.passwordConfirmTxt.text.toString().isNotEmpty()) {
                if (binding.passwordTxt.text.toString() != binding.passwordConfirmTxt.text.toString()) {
                    snackError.show()
                    binding.Password.error = "Must be the same password!"
                    binding.ConfirmPassword.error = "Must be the same password!"
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            } else {
                setPasswordError()
                setConfirmPasswordError()
                snackError.show()
            }
        }
    }

}