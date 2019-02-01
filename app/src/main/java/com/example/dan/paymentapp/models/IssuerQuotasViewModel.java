package com.example.dan.paymentapp.models;

import android.arch.lifecycle.ViewModel;

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
