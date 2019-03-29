package hr.bagy94.factorynewsreader.ui.utils

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.ContextCompat
import hr.bagy94.factorynewsreader.R


class WhiteGradientTextView @JvmOverloads constructor(context:Context, attributeSet: AttributeSet? = null, defStyleAttr:Int = 0):
        TextView(context,attributeSet,defStyleAttr){
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if(changed && lineCount > 1){
            val height = height
            val gradient = LinearGradient(0.0f,(height.toFloat()/2),0.0f,height.toFloat(),
                ContextCompat.getColor(context,R.color.gradient_start),
                ContextCompat.getColor(context, R.color.gradient_end),
                Shader.TileMode.CLAMP)
            paint.shader = gradient
        }
    }
}