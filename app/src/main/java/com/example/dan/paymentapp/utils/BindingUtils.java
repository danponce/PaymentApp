package com.example.dan.paymentapp.utils;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.ObservableInt;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingUtils
{
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url)
    {
        Glide.with(view).load(url).into(view);
    }

    @BindingAdapter("android:text")
    public static void bindIntegerInText(EditText tv, int value)
    {
        if(value == 0)
            return;

        tv.setText(String.valueOf(value));

        // Set the cursor to the end of the text
        tv.setSelection(tv.getText().length());
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getIntegerFromBinding(EditText view)
    {
        String string = view.getText().toString();

        return string.isEmpty() ? 0 : Integer.parseInt(string);
    }
}
