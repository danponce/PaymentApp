package com.example.dan.paymentapp.models;

import com.google.gson.annotations.SerializedName;

public class PaymentIssuerQuota
{
    private int installments;

    @SerializedName("recommended_message")
    private String recommendedMessage;

    public int getInstallments()
    {
        return installments;
    }

    public void setInstallments(int installments)
    {
        this.installments = installments;
    }

    public String getRecommendedMessage()
    {
        return recommendedMessage;
    }

    public void setRecommendedMessage(String recommendedMessage)
    {
        this.recommendedMessage = recommendedMessage;
    }
}
