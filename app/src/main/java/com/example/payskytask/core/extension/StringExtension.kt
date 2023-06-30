package com.example.payskytask.core.extension

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import com.example.payskytask.R
import com.example.payskytask.modules.cards.core.entity.CardType

fun String.detectCardType(): CardType {
    return when {
        this.startsWith("4") -> CardType.VISA
        this.matches( Regex("^5[1-5]\$")) -> CardType.MASTERCARD
        this.matches(Regex("^[5,9][0-9]\$")) -> CardType.MEEZA
        else -> CardType.UNKNOWN
    }
}
 fun CardType.getCardLogoResource(): Int {
    return when (this) {
        CardType.VISA -> R.drawable.ic_visa
        CardType.MASTERCARD -> R.drawable.ic_master_card
        CardType.MEEZA -> R.drawable.ic_meeza
        else -> R.drawable.ic_credit_card
    }
}

fun String.base64ToDrawable(): Drawable? {
    try {
        val decodedBytes = Base64.decode(this, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        return BitmapDrawable(Resources.getSystem(), bitmap)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}