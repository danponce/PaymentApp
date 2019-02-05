package com.example.dan.paymentapp.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils
{
    public static void showShortToast(Context context, int stringRes)
    {
        Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show();
    }
}
