package com.example.dan.paymentapp.models;

import java.util.List;

public class PaymentIssuer
{
    private List<PaymentIssuerQuota> payer_costs;

    public List<PaymentIssuerQuota> getPayer_costs()
    {
        return payer_costs;
    }

    public void setPayer_costs(List<PaymentIssuerQuota> payer_costs)
    {
        this.payer_costs = payer_costs;
    }
}
