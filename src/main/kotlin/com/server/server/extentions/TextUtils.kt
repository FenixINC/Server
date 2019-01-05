package com.server.server.extentions

import javax.annotation.Nullable

object TextUtils {
    fun isEmpty(@Nullable str: CharSequence?): Boolean {
        return str == null || str.isEmpty()
    }
}