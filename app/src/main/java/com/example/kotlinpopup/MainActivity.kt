package com.example.kotlinpopup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinpopup.databinding.ActivityMainBinding
import com.example.kotlinpopup.popup.MyPopupFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding : ActivityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.basicPopupBtn.setOnClickListener {
            MyPopupFactory.showBasicPopup(this)
        }

        dataBinding.oneBtnPopup.setOnClickListener {
            MyPopupFactory.showOneBtnPopup(this)
        }
    }

    override fun onBackPressed() {
        MyPopupFactory.showFinishPopup(this)
    }
}
