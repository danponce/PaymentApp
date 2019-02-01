package com.example.dan.paymentapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.models.PaymentMethod;
import com.example.dan.paymentapp.databinding.ItemPaymentMethodBinding;

import java.util.List;

public class PaymentMethodRecyclerAdapter extends RecyclerView.Adapter<PaymentMethodViewHolder>
{
    private List<PaymentMethod> mPaymentMethodList;
    private MethodRecyclerClickListener mMethodClickListener;

    public PaymentMethodRecyclerAdapter(List<PaymentMethod> mPaymentMethodList, MethodRecyclerClickListener listener)
    {
        this.mPaymentMethodList = mPaymentMethodList;
        this.mMethodClickListener = listener;
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
        final PaymentMethod method = mPaymentMethodList.get(i);

        paymentMethodViewHolder.getBinding().card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                for (PaymentMethod paymentMethod : mPaymentMethodList)
                    paymentMethod.isSelected.set(false);

                method.isSelected.set(true);

                mMethodClickListener.onMethodClick(method);
            }
        });
        paymentMethodViewHolder.bind(method);
    }

    @Override
    public int getItemCount()
    {
        return mPaymentMethodList.size();
    }
}
