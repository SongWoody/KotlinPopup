package com.example.kotlinpopup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinpopup.databinding.ActivityMainBinding
import com.example.popup.MyPopup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding : ActivityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.button.setOnClickListener() {
            MyPopup.builder()
                .setTitle("Title")
                .setBody("Body")
                .setLeftBtnText("Left") { dialog -> dialog.dismiss() }
                .setRightBtnText("Right") { dialog -> dialog.dismiss() }
                .create(this).show()
        }
    }
}
