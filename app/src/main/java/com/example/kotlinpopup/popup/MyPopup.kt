package com.example.popup

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.example.kotlinpopup.R
import com.example.kotlinpopup.databinding.ViewPopupBinding

typealias ButtonCallback = (MyPopup) -> Unit
class MyPopup private constructor(context: Context, private val popupParams: MyPopupParams): Dialog(context){

    companion object {
        fun builder() = MyPopupBuilder()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
        }

        val dataBinding : ViewPopupBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_popup, null, false)
        setContentView(dataBinding.root)

        apply(dataBinding)
    }

    private fun apply(dataBinding: ViewPopupBinding) {
        setCancelable(popupParams.cancelable)

        dataBinding.let {
            it.textTitle.text = popupParams.titleText
            it.textBody.text = popupParams.bodyText
            it.btnLeft.text = popupParams.leftBtnText
            it.btnLeft.setOnClickListener() {
                popupParams.leftBtnCallback(this)
            }
            it.btnRight.text = popupParams.rightBtnText
            it.btnRight.setOnClickListener() {
                popupParams.rightBtnCallback(this)
            }
        }
    }

    /**
     * MyPopup Builder Class
     */
    class MyPopupBuilder () {
        private val myPopupParams = MyPopupParams()

        fun setCancelable(isCancel: Boolean) = apply { myPopupParams.cancelable = isCancel }
        fun setTitle(str: String) = apply { myPopupParams.titleText = str }
        fun setBody(str: String) = apply { myPopupParams.bodyText = str }
        fun setLeftBtnText(str: String, callback: ButtonCallback) = apply {
            myPopupParams.apply {
                leftBtnText = str
                leftBtnCallback = callback
            }
        }
        fun setRightBtnText(str: String, callback: ButtonCallback) = apply {
            myPopupParams.apply {
                rightBtnText = str
                rightBtnCallback = callback
            }
        }

        fun create(context: Context): MyPopup {
            return MyPopup(context, myPopupParams)
        }
    }

    /**
     * MyPopup Params Class
     */
    private class MyPopupParams {
        var cancelable = true
        var titleText: String = ""

        var bodyText: String = ""

        var leftBtnText: String = ""
        var leftBtnCallback: ButtonCallback = {}

        var rightBtnText: String = ""
        var rightBtnCallback: ButtonCallback = {}
    }
}