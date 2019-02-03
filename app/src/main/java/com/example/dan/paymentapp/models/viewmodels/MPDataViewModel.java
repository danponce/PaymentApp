package com.example.dan.paymentapp.models.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.example.dan.paymentapp.models.PaymentBank;
import com.example.dan.paymentapp.models.PaymentIssuerQuota;
import com.example.dan.paymentapp.models.PaymentMethod;

public class MPDataViewModel extends ViewModel
{
    public final ObservableField<String> paymentMethod = new ObservableField<>();
    public final ObservableInt paymentBank = new ObservableInt();
    public final ObservableInt paymentIssuerQuotas = new ObservableInt();
    private PaymentBank bank;
    private PaymentMethod method;
    private PaymentIssuerQuota quota;

    public PaymentMethod getMethod()
    {
        return method;
    }

    public void setMethod(PaymentMethod method)
    {
        this.method = method;
    }

    public PaymentBank getBank()
    {
        return bank;
    }

    public void setBank(PaymentBank bank)
    {
        this.bank = bank;
    }

    public PaymentIssuerQuota getQuota()
    {
        return quota;
    }

    public void setQuota(PaymentIssuerQuota quota)
    {
        this.quota = quota;
    }
}
