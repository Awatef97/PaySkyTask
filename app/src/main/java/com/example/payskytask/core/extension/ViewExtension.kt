import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.text.Editable
import android.text.InputFilter
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Base64
import android.view.View
import android.widget.EditText
import androidx.core.graphics.drawable.toBitmap
import java.io.ByteArrayOutputStream
import java.net.URL

fun EditText.validateExpiryDate() {
    val expiryDateTextWatcher = object : TextWatcher {
        private var isFormatting = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (isFormatting) return

            isFormatting = true

            removeTextChangedListener(this)

            val input = s.toString()

            val formattedInput = formatExpiryDate(input)

            setText(formattedInput)
            setSelection(formattedInput.length)
            addTextChangedListener(this)

            isFormatting = false
        }

        private fun formatExpiryDate(input: String): String {
            val digitsOnly = input.replace("\\D+".toRegex(), "")

            val formattedInput = StringBuilder(digitsOnly)

            if (formattedInput.length > 2) {
                formattedInput.insert(2, "/")
            }

            return formattedInput.toString()
        }
    }

    filters = arrayOf(InputFilter.LengthFilter(5))

    addTextChangedListener(expiryDateTextWatcher)
}

fun EditText.validateCardHolder() {
    val allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ '-"
    filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
        val builder = StringBuilder()
        for (element in source) {
            val c = element
            if (allowedCharacters.contains(c)) {
                builder.append(c)
            }
        }
        SpannableStringBuilder.valueOf(builder.toString())
    })

}

fun Drawable.drawableToString(): String {
    val bitmap: Bitmap = if (this is BitmapDrawable) {
        this.bitmap
    } else {
        this.toBitmap()
    }

    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()

    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun View.handleVisibility(isLoading: Boolean) =
    if (isLoading) this.visibility = View.VISIBLE
    else this.visibility = View.GONE

fun Context.isInternetAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}