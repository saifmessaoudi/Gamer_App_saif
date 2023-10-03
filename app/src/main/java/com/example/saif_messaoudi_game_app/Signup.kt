package com.example.saif_messaoudi_game_app

import android.content.Intent
import android.os.Bundle
import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.saif_messaoudi_game_app.databinding.SignupActivityBinding
import com.google.android.material.snackbar.Snackbar


class Signup : AppCompatActivity()  {

    private lateinit var binding : SignupActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignupActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeLinkClickableInsideAstring()

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
        setFullNameInputEmptyError()
        setEmailInputEmptyError()
        setPasswordInputEmptyError()
        setConfirmPasswordInputEmptyError()
    }
    private fun setFullNameInputEmptyError() {
        binding.fullAccountTXT.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus)
                setFullNameError()
        }
    }
    private fun setFullNameError() {
        if (binding.fullAccountTXT.text.toString().isEmpty()) {
            binding.FullName.error = getString(R.string.not_empty)
        } else if (binding.fullAccountTXT.text.toString().length < 6) {
            binding.FullName.error = "Must be at least 6 characters!"
        } else {
            binding.FullName.error = null
        }
    }
    private fun setEmailInputEmptyError() {
        binding.emailTxt.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus)
                setEmailError()
        }
    }
    private fun setEmailError() {
        if (binding.emailTxt.text.toString().isEmpty()) {
            binding.EmailAddress.error = getString(R.string.not_empty)
        } else {
            binding.EmailAddress.error = null
        }
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

    // Clickable Links inside a String START *************************
    fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
        val spannableString = SpannableString(this.text)
        var startIndexOfLink = -1
        for (link in links) {
            val clickableSpan = object : ClickableSpan() {
                //This empty method, if not present text turn white
                override fun updateDrawState(textPaint: TextPaint) {
                    // Text Decorations
                    // textPaint.color = Color.BLUE
                    // textPaint.isUnderlineText = true
                }

                override fun onClick(view: View) {
                    Selection.setSelection((view as TextView).text as Spannable, 0)
                    view.invalidate()
                    link.second.onClick(view)
                }
            }
            startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
            spannableString.setSpan(
                clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        this.movementMethod =
            LinkMovementMethod.getInstance() // Without LinkMovementMethod, links cannot be clicked
        this.setText(spannableString, TextView.BufferType.SPANNABLE)
    }

    private fun makeLinkClickableInsideAstring() {
        val snack = Snackbar.make(binding.contextView,"Coming soon :)", Snackbar.LENGTH_LONG)
            .setTextColor(ContextCompat.getColor(this, R.color.white))
        // Call the makeLinks function to create clickable links
        binding.tremAndPolicy.makeLinks(
            Pair("Terms & \nConditions", View.OnClickListener {
                snack.show()
            }),
            Pair("Privacy Policy", View.OnClickListener {
                snack.show()
            })
        )
    }
    // Clickable Links inside a String END ***************************

    private fun initSubmitBtn() {
        val snackError = Snackbar.make(binding.contextView,"You have some errors in your inputs!", Snackbar.LENGTH_LONG)
            .setTextColor(ContextCompat.getColor(this, R.color.white))

        binding.btnSubmit.setOnClickListener {
            if (binding.fullAccountTXT.text.toString().isNotEmpty() && binding.emailTxt.text.toString().isNotEmpty()
                && binding.passwordTxt.text.toString().isNotEmpty() && binding.passwordConfirmTxt.text.toString().isNotEmpty()) {
                if (binding.fullAccountTXT.text.toString().length < 6) {
                    binding.FullName.error = "Must be at least 6 characters!"
                }else if (binding.passwordTxt.text.toString() != binding.passwordConfirmTxt.text.toString()) {
                    snackError.show()
                    binding.Password.error = "Must be the same password!"
                    binding.ConfirmPassword.error = "Must be the same password!"
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            } else {
                setFullNameError()
                setEmailError()
                setPasswordError()
                setConfirmPasswordError()
                snackError.show()
            }
        }
    }

}