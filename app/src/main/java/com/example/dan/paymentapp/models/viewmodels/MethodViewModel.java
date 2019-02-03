package com.example.dan.paymentapp.models.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.example.dan.paymentapp.models.PaymentMethod;

import java.util.List;

public class MethodViewModel extends ViewModel
{
    private List<PaymentMethod> paymentMethodList;

    public List<PaymentMethod> getPaymentMethodList()
    {
        return paymentMethodList;
    }

    public void setPaymentMethodList(List<PaymentMethod> paymentMethodList)
    {
        this.paymentMethodList = paymentMethodList;
    }
}
