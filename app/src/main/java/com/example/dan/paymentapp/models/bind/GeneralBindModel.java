package com.example.dan.paymentapp.models.bind;

import android.databinding.ObservableBoolean;

/**
 * Used mainly for managing layout when
 * something is loading from the network
 */
public class GeneralBindModel
{
    public final ObservableBoolean isLoading = new ObservableBoolean();
}
