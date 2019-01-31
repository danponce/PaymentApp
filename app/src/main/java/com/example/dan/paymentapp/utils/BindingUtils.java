package com.example.dan.paymentapp.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingUtils
{
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url)
    {
        Glide.with(view).load(url).into(view);
    }
}
