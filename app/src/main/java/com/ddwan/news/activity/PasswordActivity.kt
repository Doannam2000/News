package com.ddwan.news.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ddwan.news.R
import com.ddwan.news.config.Constants.Companion.START_ACTIVITY
import com.ddwan.news.viewmodel.PassViewModel
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel by lazy {
        ViewModelProvider(this)[PassViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        supportActionBar?.hide()
        viewModel.isStartActivity = intent.getBooleanExtra(START_ACTIVITY, false)
        observe()
        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btnC.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        viewModel.check.observe(this, {
            when (it) {
                0 -> textDisplay.text = "Nhập mật khẩu"
                1 -> startActivity(Intent(this,
                    MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                2 -> textDisplay.text = "Mật khẩu không đúng"
                3 -> textDisplay.text = "Thay đổi thành công"
                4 -> textDisplay.text = "Nhập mật khẩu mới"
                5 -> finish()
            }
        })
        viewModel.imageDrawable.observe(this, {
            when (it) {
                0 -> imagePass.setImageResource(R.drawable.none)
                1 -> imagePass.setImageResource(R.drawable.pass_one)
                2 -> imagePass.setImageResource(R.drawable.pass_two)
                3 -> imagePass.setImageResource(R.drawable.pass_three)
                4 -> imagePass.setImageResource(R.drawable.pass_four)
            }
        })
    }

    override fun onBackPressed() {
        this.finishAffinity()
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btn0 -> viewModel.checkPass(0)
            btn1 -> viewModel.checkPass(1)
            btn2 -> viewModel.checkPass(2)
            btn3 -> viewModel.checkPass(3)
            btn4 -> viewModel.checkPass(4)
            btn5 -> viewModel.checkPass(5)
            btn6 -> viewModel.checkPass(6)
            btn7 -> viewModel.checkPass(7)
            btn8 -> viewModel.checkPass(8)
            btn9 -> viewModel.checkPass(9)
            btnC -> viewModel.checkPass(10)
        }
    }

}