package com.example.dan.paymentapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentIssuer
{
    @SerializedName("payer_costs")
    private List<PaymentIssuerQuota> payerCosts;

    public List<PaymentIssuerQuota> getPayerCosts()
    {
        return payerCosts;
    }

    public void setPayerCosts(List<PaymentIssuerQuota> payerCosts)
    {
        this.payerCosts = payerCosts;
    }
}
