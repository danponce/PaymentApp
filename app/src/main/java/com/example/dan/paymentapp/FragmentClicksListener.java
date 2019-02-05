package com.example.dan.paymentapp;

import android.view.View;

public interface FragmentClicksListener
{
    void previousFragment(int fragmentId);
    void nextFragment(int fragmentId, View view);
}
