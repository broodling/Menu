package com.android.menu.ui

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs

fun showSimpleDialog(title: String, description: String, context: Context): AlertDialog {
    return MaterialAlertDialogBuilder(context)
        .setTitle(title)
        .setMessage(description)
        .setPositiveButton(
            "OK"
        ) { dialog, which -> dialog.dismiss() }
        .show()
}