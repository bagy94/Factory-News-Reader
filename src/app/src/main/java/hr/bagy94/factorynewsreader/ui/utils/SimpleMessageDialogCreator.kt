package hr.bagy94.factorynewsreader.ui.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


object SimpleMessageDialogCreator {

    fun create(title:String, message:String,closeButtonLabel:String,context: Context):AlertDialog{
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(closeButtonLabel) { dialog, _ ->  dialog.dismiss()}
        return builder.create()
    }
}