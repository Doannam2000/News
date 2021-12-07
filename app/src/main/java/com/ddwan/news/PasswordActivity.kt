package com.ddwan.news

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ddwan.news.viewmodel.PassViewModel
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[PassViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        supportActionBar?.hide()
        viewModel.isStartActivity = intent.getBooleanExtra("isStartActivity", false)
        observe()
        btn0.setOnClickListener { viewModel.checkPass(0) }
        btn1.setOnClickListener { viewModel.checkPass(1) }
        btn2.setOnClickListener { viewModel.checkPass(2) }
        btn3.setOnClickListener { viewModel.checkPass(3) }
        btn4.setOnClickListener { viewModel.checkPass(4) }
        btn5.setOnClickListener { viewModel.checkPass(5) }
        btn6.setOnClickListener { viewModel.checkPass(6) }
        btn7.setOnClickListener { viewModel.checkPass(7) }
        btn8.setOnClickListener { viewModel.checkPass(8) }
        btn9.setOnClickListener { viewModel.checkPass(9) }
        btnC.setOnClickListener { viewModel.checkPass(10) }
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

}