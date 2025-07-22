package com.example.iosdialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import com.example.iosdialog.databinding.AlertsTwoButtonsBinding

class IosDialog(
    context: Context,
    title: String,
    subtitle: String,
    bold: Boolean,
    typeFace: Typeface?,
    cancelable: Boolean,
    typeFace2: Typeface?
) {
    private val dialog: Dialog = Dialog(context)
    private val binding: AlertsTwoButtonsBinding
    private var positiveListener: iOSDialogClickListener? = null
    private var negativeListener: iOSDialogClickListener? = null
    private var negativeExist = false

    init {
        val inflater = dialog.layoutInflater
        binding = AlertsTwoButtonsBinding.inflate(inflater)
        dialog.setContentView(binding.root)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(cancelable)
        setTitle(title)
        setSubtitle(subtitle)
        setBoldPositiveLabel(bold)
        setTypefaces(typeFace)
        setTypefaces2(typeFace2)
        initEvents()
    }

    fun setPositive(okLabel: String, listener: iOSDialogClickListener) {
        this.positiveListener = listener
        dismiss()
        setPositiveLabel(okLabel)
    }

    fun setNegative(koLabel: String, listener: iOSDialogClickListener?) {
        if (listener != null) {
            this.negativeListener = listener
            dismiss()
            negativeExist = true
            setNegativeLabel(koLabel)
        }
    }

    fun show() {
        if (!negativeExist) {
            binding.dialogButtonNO.visibility = View.GONE
            binding.separator.visibility = View.GONE
        }
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }

    fun setTitle(title: String) {
        binding.title.text = title
    }

    fun setSubtitle(subtitle: String) {
        binding.subtitle.text = subtitle
    }

    private fun setPositiveLabel(positive: String) {
        binding.dialogButtonOK.text = positive
    }

    private fun setNegativeLabel(negative: String) {
        binding.dialogButtonNO.text = negative
    }

    private fun setBoldPositiveLabel(bold: Boolean) {
        binding.dialogButtonOK.setTypeface(null, if (bold) Typeface.BOLD else Typeface.NORMAL)
    }

    private fun setTypefaces(appleFont: Typeface?) {
        appleFont?.let {
            binding.subtitle.typeface = it
            binding.dialogButtonOK.typeface = it
            binding.dialogButtonNO.typeface = it
        }
    }

    private fun setTypefaces2(appleFont: Typeface?) {
        appleFont?.let {
            binding.title.typeface = it
        }
    }

    private fun initEvents() {
        binding.dialogButtonOK.setOnClickListener {
            positiveListener?.onClick(this)
        }

        binding.dialogButtonNO.setOnClickListener {
            negativeListener?.onClick(this)
        }
    }
}
