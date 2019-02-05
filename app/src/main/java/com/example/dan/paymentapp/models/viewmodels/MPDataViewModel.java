package com.example.dan.paymentapp.models.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.example.dan.paymentapp.models.PaymentBank;
import com.example.dan.paymentapp.models.PaymentIssuerQuota;
import com.example.dan.paymentapp.models.PaymentMethod;

public class MPDataViewModel extends ViewModel
{
    public final ObservableField<String> paymentMethod = new ObservableField<>();
    public final ObservableInt paymentAmmount = new ObservableInt();
    public final ObservableInt paymentBank = new ObservableInt();
    public final ObservableInt paymentIssuerQuotas = new ObservableInt();

    // To know if the process is finished by the user
    public final ObservableBoolean isFinished = new ObservableBoolean();
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

    // Reset the values to start a new payment
    public void reset()
    {
        paymentAmmount.set(0);
        paymentMethod.set("");
        paymentBank.set(0);
        paymentIssuerQuotas.set(0);
        bank = null;
        method = null;
        quota = null;

    }
}
