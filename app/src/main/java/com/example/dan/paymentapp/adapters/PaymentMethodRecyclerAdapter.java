package com.example.dan.paymentapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dan.paymentapp.PaymentMethod;

import java.util.List;

public class PaymentMethodRecyclerAdapter extends RecyclerView.Adapter<PaymentMethodViewHolder>
{
    private List<PaymentMethod> mPaymentMethodList;

    public PaymentMethodRecyclerAdapter(List<PaymentMethod> mPaymentMethodList)
    {
        this.mPaymentMethodList = mPaymentMethodList;
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder paymentMethodViewHolder, int i)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }
}
