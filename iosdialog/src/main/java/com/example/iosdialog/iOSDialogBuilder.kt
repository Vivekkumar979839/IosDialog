package com.example.iosdialog

import android.content.Context
import android.graphics.Typeface

class iOSDialogBuilder(private val context: Context) {

    private var tf: Typeface? = null
    private var tf1: Typeface? = null
    private var bold: Boolean = false
    private var cancelable: Boolean = true
    private var title: String = ""
    private var subtitle: String = ""
    private var okLabel: String = ""
    private var koLabel: String = ""
    private var positiveListener: iOSDialogClickListener? = null
    private var negativeListener: iOSDialogClickListener? = null
    private var dialog: IosDialog? = null

    fun setTitle(title: String) = apply {
        this.title = title
    }

    fun setSubtitle(subtitle: String) = apply {
        this.subtitle = subtitle
    }

    fun setBoldPositiveLabel(bold: Boolean) = apply {
        this.bold = bold
    }

    fun setFont(font: Typeface) = apply {
        this.tf = font
    }

    fun setTitleFont(font: Typeface) = apply {
        this.tf1 = font
    }

    fun setCancelable(cancelable: Boolean) = apply {
        this.cancelable = cancelable
    }

    fun setNegativeListener(koLabel: String, listener: iOSDialogClickListener?) = apply {
        this.koLabel = koLabel
        this.negativeListener = listener
    }

    fun setPositiveListener(okLabel: String, listener: iOSDialogClickListener?) = apply {
        this.okLabel = okLabel
        this.positiveListener = listener
    }

    fun dismissDialog() {
        try {
            dialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun build(): IosDialog {
        dialog = IosDialog(context, title, subtitle, bold, tf, cancelable, tf1)
        dialog?.setNegative(koLabel, negativeListener)
        dialog?.setPositive(okLabel, positiveListener!!)
        return dialog!!
    }
}
