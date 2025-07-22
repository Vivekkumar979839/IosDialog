package com.example.androidlibrary

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidlibrary.databinding.ActivityMainBinding
import com.example.iosdialog.IosDialog
import com.example.iosdialog.iOSDialogBuilder
import com.example.iosdialog.iOSDialogClickListener

class MainActivity : AppCompatActivity() {
    private var dialog: IosDialog? = null
    private lateinit var context:Context
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context=this
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.clickMe.setOnClickListener {

            showDialog(context,"This is Latest Android dialog")
        }
    }

    fun showDialog(context: Context, message: String?) {
        val setTitle = context.getString(R.string.app_name)

        if (dialog == null) {
            dialog = iOSDialogBuilder(context)
                .setTitle(setTitle)
                .setSubtitle(message ?: "")
                .setBoldPositiveLabel(false)
                .setCancelable(false)
                .setPositiveListener("ok", object : iOSDialogClickListener {
                    override fun onClick(dialog: IosDialog) {
                        dialog.dismiss()
                    }
                })
                .setNegativeListener("cancel", object : iOSDialogClickListener {
                    override fun onClick(dialog: IosDialog) {
                        dialog.dismiss()
                    }
                })
                .build()
        } else {
            dialog?.setSubtitle(message ?: "")
        }

        dialog?.show()
    }


}