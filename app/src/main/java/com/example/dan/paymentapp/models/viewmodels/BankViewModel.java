package com.example.dan.paymentapp.models.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.example.dan.paymentapp.models.PaymentBank;

import java.util.List;

public class BankViewModel extends ViewModel
{
    private List<PaymentBank> paymentBankList;

    public List<PaymentBank> getPaymentBankList()
    {
        return paymentBankList;
    }

    public void setPaymentBankList(List<PaymentBank> paymentBankList)
    {
        this.paymentBankList = paymentBankList;
    }
}
