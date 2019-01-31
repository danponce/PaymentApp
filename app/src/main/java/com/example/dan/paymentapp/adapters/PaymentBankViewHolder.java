package com.example.dan.paymentapp.adapters;

import android.support.v7.widget.RecyclerView;

import com.example.dan.paymentapp.databinding.ItemPaymentBankBinding;
import com.example.dan.paymentapp.databinding.ItemPaymentMethodBinding;
import com.example.dan.paymentapp.models.PaymentBank;
import com.example.dan.paymentapp.models.PaymentMethod;

public class PaymentBankViewHolder extends RecyclerView.ViewHolder
{
    private final ItemPaymentBankBinding binding;

    public PaymentBankViewHolder(ItemPaymentBankBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ItemPaymentBankBinding getBinding()
    {
        return binding;
    }

    public void bind(PaymentBank bank) {
        binding.setBank(bank);
        binding.executePendingBindings();
    }
}
