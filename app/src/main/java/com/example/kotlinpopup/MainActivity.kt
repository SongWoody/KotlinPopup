package com.example.kotlinpopup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinpopup.databinding.ActivityMainBinding
import com.example.kotlinpopup.popup.MyPopupFactory
import com.example.popup.MyPopup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding : ActivityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.button.setOnClickListener() {
            MyPopupFactory.createBasicPopup(this)
        }
    }

    override fun onBackPressed() {
        MyPopupFactory.createFinishPopup(this)
    }
}
