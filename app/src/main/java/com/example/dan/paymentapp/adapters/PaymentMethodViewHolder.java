package com.example.dan.paymentapp.adapters;

import android.support.v7.widget.RecyclerView;

import com.example.dan.paymentapp.PaymentMethod;
import com.example.dan.paymentapp.databinding.ItemPaymentMethodBinding;

public class PaymentMethodViewHolder extends RecyclerView.ViewHolder
{
    private final ItemPaymentMethodBinding binding;

    public PaymentMethodViewHolder(ItemPaymentMethodBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(PaymentMethod method) {
        binding.setMethod(method);
        binding.executePendingBindings();
    }
}
