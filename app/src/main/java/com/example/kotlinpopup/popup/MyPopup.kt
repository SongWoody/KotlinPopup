package com.example.popup

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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

        dataBinding.apply {
            textTitle.text = popupParams.titleText
            textBody.text = popupParams.bodyText
            btnLeft.apply {
                visibility = if (popupParams.leftBtnHidden) View.GONE else View.VISIBLE
                text = popupParams.leftBtnText
                setOnClickListener() {
                    popupParams.leftBtnCallback(this@MyPopup)
                }
            }
            btnRight.apply {
                visibility = if (popupParams.rightBtnHidden) View.GONE else View.VISIBLE
                text = popupParams.rightBtnText
                setOnClickListener() {
                    popupParams.rightBtnCallback(this@MyPopup)
                }
            }
        }

    }

    /**
     * MyPopup Builder Class
     */
    class MyPopupBuilder {
        private val myPopupParams = MyPopupParams()

        fun setCancelable(isCancel: Boolean) = apply { myPopupParams.cancelable = isCancel }
        fun setTitle(str: String) = apply { myPopupParams.titleText = str }
        fun setBody(str: String) = apply { myPopupParams.bodyText = str }
        fun setLeftBtnHidden(isHidden: Boolean) = apply { myPopupParams.leftBtnHidden = isHidden }
        fun setLeftBtnText(str: String, callback: ButtonCallback) = apply {
            myPopupParams.apply {
                leftBtnHidden = false
                leftBtnText = str
                leftBtnCallback = callback
            }
        }
        fun setRightBtnHidden(isHidden: Boolean)  = apply { myPopupParams.rightBtnHidden = isHidden }
        fun setRightBtnText(str: String, callback: ButtonCallback) = apply {
            myPopupParams.apply {
                rightBtnHidden = false
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

        var leftBtnHidden: Boolean = true
        var leftBtnText: String = ""
        var leftBtnCallback: ButtonCallback = {}

        var rightBtnHidden = true
        var rightBtnText: String = ""
        var rightBtnCallback: ButtonCallback = {}
    }
}