package com.example.kotlinpopup.popup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.popup.MyPopup

object MyPopupFactory {


    fun createBasicPopup(context: Context) {
        MyPopup.builder()
            .setCancelable(false)
            .setTitle("Title")
            .setBody("Body")
            .setLeftBtnText("Left") { dialog -> dialog.dismiss() }
            .setRightBtnText("Right") { dialog -> dialog.dismiss() }
            .create(context).show()
    }

    fun createFinishPopup(activity: AppCompatActivity) {
        MyPopup.builder()
            .setTitle("Activity Close")
            .setBody("Do you want to close the activity?")
            .setLeftBtnText("cancel") { dialog -> dialog.dismiss() }
            .setRightBtnText("ok") { dialog ->
                dialog.dismiss()
                activity.finish()
            }
            .create(activity).show()
    }
}