package com.bigthinkapps.sweatworks.extension

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.goIntent(clazz: Class<*>, extras: Bundle = Bundle(), finish: Boolean = false) {
    val intent = Intent(this, clazz)
    intent.putExtras(extras)
    startActivity(intent)
    if (finish) {
        finish()
    }
}