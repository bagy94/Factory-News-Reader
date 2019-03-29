package hr.bagy94.factorynewsreader.ui.utils

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import hr.bagy94.factorynewsreader.R

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url:String){
    if(url.isNotEmpty()){
        Picasso.get().load(url).error(R.drawable.ic_missing_image_black_64dp).fit().into(this)
    }else{
        setImageResource(R.drawable.ic_missing_image_black_64dp)
    }
}
