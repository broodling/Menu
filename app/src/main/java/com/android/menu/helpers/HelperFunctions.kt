package com.android.menu.helpers

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

inline fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

fun EditText.addOnTextChangeListener(onTextChange: (String?) -> Unit) {
    addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            onTextChange(s.toString())
        }

    })
}