package com.example.dan.paymentapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dan.paymentapp.PaymentMethod;
import com.example.dan.paymentapp.databinding.ItemPaymentMethodBinding;

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
        ItemPaymentMethodBinding binding = ItemPaymentMethodBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        return new PaymentMethodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder paymentMethodViewHolder, int i)
    {
        paymentMethodViewHolder.bind(mPaymentMethodList.get(i));
    }

    @Override
    public int getItemCount()
    {
        return mPaymentMethodList.size();
    }
}
