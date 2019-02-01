package com.example.dan.paymentapp.models;

import com.google.gson.annotations.SerializedName;

public class PaymentIssuerQuota
{
    private int installments;

    @SerializedName("recommended_message")
    private String recommendedMessage;
}
