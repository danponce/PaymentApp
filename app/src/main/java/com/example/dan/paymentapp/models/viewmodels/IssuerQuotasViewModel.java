package com.example.dan.paymentapp.models.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.example.dan.paymentapp.models.PaymentIssuer;

public class IssuerQuotasViewModel extends ViewModel
{
    private PaymentIssuer paymentIssuer;

    public PaymentIssuer getPaymentIssuer()
    {
        return paymentIssuer;
    }

    public void setPaymentIssuer(PaymentIssuer paymentIssuer)
    {
        this.paymentIssuer = paymentIssuer;
    }
}
