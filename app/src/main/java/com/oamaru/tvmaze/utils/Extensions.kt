package com.oamaru.tvmaze

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.oamaru.tvmaze.utils.SingleLiveEvent

@BindingAdapter(value = ["android:glide", "android:placeholder"])
fun ImageView.glide(url: String?, @DrawableRes placeholder: Int) {
    Glide.with(context).load(url).fallback(placeholder).into(this)
}

inline fun <T> T?.ifNull(defaultValue: () -> T): T {
    return this ?: defaultValue()
}

@BindingAdapter("android:isVisible")
fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

typealias Event<T> = SingleLiveEvent<T>
typealias SimpleEvent = Event<Unit>

fun <T> Event<T>.trigger(value: T) = postValue(value)
fun SimpleEvent.trigger() = postValue(Unit)
