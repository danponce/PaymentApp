package com.example.dan.paymentapp.fragments;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.dan.paymentapp.MainActivity;
import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.adapters.PaymentQuotasArrayAdapter;
import com.example.dan.paymentapp.databinding.FragmentIssuerQuotasBinding;
import com.example.dan.paymentapp.models.IssuerBindModel;
import com.example.dan.paymentapp.models.IssuerQuotasViewModel;
import com.example.dan.paymentapp.models.PaymentIssuer;
import com.example.dan.paymentapp.models.PaymentIssuerQuota;
import com.example.dan.paymentapp.network.MPPaymentService;
import com.example.dan.paymentapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Screen for selecting the number of quotas (installments)
 */
public class IssuerQuotasFragment extends BaseFragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentIssuerQuotasBinding mBinding;
    private IssuerBindModel mBindModel;

    private IssuerQuotasViewModel mIssuerViewModel;

    public IssuerQuotasFragment()
    {
        // Required empty public constructor
    }

    public static IssuerQuotasFragment newInstance(String param1, String param2)
    {
        IssuerQuotasFragment fragment = new IssuerQuotasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mBinding = FragmentIssuerQuotasBinding.inflate(inflater, container, false);

        mBindModel = new IssuerBindModel();

        mIssuerViewModel = ViewModelProviders.of(this).get(IssuerQuotasViewModel.class);

        mBinding.setModel(mBindModel);
        mBinding.setId(getFragmentId());

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        if(mIssuerViewModel.getPaymentIssuer() != null)
            setQuotasSpinner(mIssuerViewModel.getPaymentIssuer());
        else
            getIssuerQuotas();
    }

    private void getIssuerQuotas()
    {
        MPPaymentService service = RetrofitClient.getRetrofitInstance().create(MPPaymentService.class);

        Call<List<PaymentIssuer>> call = service.getPaymentIssuerQuotasInfo(getString(R.string.mp_public_key),
                "visa",
                20000,
                288);

        call.enqueue(new Callback<List<PaymentIssuer>>()
        {
            @Override
            public void onResponse(Call<List<PaymentIssuer>> call, Response<List<PaymentIssuer>> response)
            {
                PaymentIssuer issuer = response.body().get(0);

                mIssuerViewModel.setPaymentIssuer(issuer);

                setQuotasSpinner(issuer);
            }

            @Override
            public void onFailure(Call<List<PaymentIssuer>> call, Throwable t)
            {

            }
        });

    }

    private void setQuotasSpinner(PaymentIssuer issuer)
    {
        mBinding.quotasSpinner.setAdapter(new PaymentQuotasArrayAdapter(getActivity(), R.layout.item_quota, issuer.getPayerCosts()));
        mBinding.quotasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                PaymentIssuerQuota quota = (PaymentIssuerQuota) parent.getItemAtPosition(position);

                // Set the new recommended message to show
                mBindModel.message.set(quota.getRecommendedMessage());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    @Override
    public int getFragmentId()
    {
        return MainActivity.FRAGMENT_ISSUER_QUOTAS;
    }
}
