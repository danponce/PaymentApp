package com.example.dan.paymentapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.MainActivity;
import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.adapters.PaymentQuotasArrayAdapter;
import com.example.dan.paymentapp.databinding.FragmentIssuerQuotasBinding;
import com.example.dan.paymentapp.models.PaymentIssuer;
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


    public IssuerQuotasFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IssuerQuotasFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        getIssuerQuotas();
    }

    private void getIssuerQuotas()
    {
        MPPaymentService service = RetrofitClient.getRetrofitInstance().create(MPPaymentService.class);

        Call<PaymentIssuer> call = service.getPaymentIssuerQuotasInfo(getString(R.string.mp_public_key),
                "visa",
                20000,
                288);

        call.enqueue(new Callback<PaymentIssuer>()
        {
            @Override
            public void onResponse(Call<PaymentIssuer> call, Response<PaymentIssuer> response)
            {
                setQuotasSpinner(response.body());
            }

            @Override
            public void onFailure(Call<PaymentIssuer> call, Throwable t)
            {

            }
        });

    }

    private void setQuotasSpinner(PaymentIssuer issuer)
    {
        mBinding.quotasSpinner.setAdapter(new PaymentQuotasArrayAdapter(getActivity(), R.layout.item_quota, issuer.getPayerCosts()));
    }

    @Override
    public int getFragmentId()
    {
        return MainActivity.FRAGMENT_ISSUER_QUOTAS;
    }
}
