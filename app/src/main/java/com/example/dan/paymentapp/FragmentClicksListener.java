package com.example.dan.paymentapp;

import android.view.View;

public interface FragmentClicksListener
{
    void previousFragment(int fragmentId, View view);
    void nextFragment(int fragmentId, View view);
}
