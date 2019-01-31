package com.example.dan.paymentapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.models.PaymentBank;
import com.example.dan.paymentapp.databinding.ItemPaymentBankBinding;


import java.util.List;

public class PaymentBankRecyclerAdapter extends RecyclerView.Adapter<PaymentBankViewHolder>
{
    private List<PaymentBank> mPaymentBankList;

    public PaymentBankRecyclerAdapter(List<PaymentBank> mPaymentBankList)
    {
        this.mPaymentBankList = mPaymentBankList;
    }

    @NonNull
    @Override
    public PaymentBankViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        ItemPaymentBankBinding binding = ItemPaymentBankBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        return new PaymentBankViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentBankViewHolder paymentMethodViewHolder, int i)
    {
        final PaymentBank method = mPaymentBankList.get(i);

        paymentMethodViewHolder.getBinding().card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                for (PaymentBank paymentBank : mPaymentBankList)
                    paymentBank.isSelected.set(false);

                method.isSelected.set(true);
            }
        });
        paymentMethodViewHolder.bind(method);
    }

    @Override
    public int getItemCount()
    {
        return mPaymentBankList.size();
    }
}
