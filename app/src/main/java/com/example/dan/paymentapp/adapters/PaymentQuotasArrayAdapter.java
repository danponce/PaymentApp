package com.example.dan.paymentapp.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.databinding.ItemQuotaBinding;
import com.example.dan.paymentapp.models.PaymentIssuerQuota;

import java.util.ArrayList;
import java.util.List;

public class PaymentQuotasArrayAdapter extends ArrayAdapter<PaymentIssuerQuota> {
    private List<PaymentIssuerQuota> mQuotas;
    private Context context;

    public PaymentQuotasArrayAdapter(Context context, int resource, List<PaymentIssuerQuota> mQuotas) {
        super(context, resource);
        this.mQuotas = mQuotas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mQuotas.size();
    }

    @Override
    public PaymentIssuerQuota getItem(int position) {
        return mQuotas.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        ItemQuotaBinding binding = DataBindingUtil.
                inflate(inflater, R.layout.item_quota, parent, false);

        //OR
        //MovieListItemBinding binding = MovieListItemBinding.inflate(inflater,null,false);

        binding.setQuota(getItem(position));

        return binding.getRoot();
    }
}
