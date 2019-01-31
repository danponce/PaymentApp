package com.example.dan.paymentapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.MainActivity;
import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.adapters.PaymentBankRecyclerAdapter;
import com.example.dan.paymentapp.databinding.FragmentBankBinding;
import com.example.dan.paymentapp.models.PaymentBank;
import com.example.dan.paymentapp.network.MPPaymentService;
import com.example.dan.paymentapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BankFragment extends BaseFragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentBankBinding mBinding;

    public BankFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BankFragment newInstance(String param1, String param2)
    {
        BankFragment fragment = new BankFragment();
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
        mBinding = FragmentBankBinding.inflate(inflater, container, false);

        getPaymentBanks();

        return mBinding.getRoot();
    }

    private void getPaymentBanks()
    {
        MPPaymentService service = RetrofitClient.getRetrofitInstance().create(MPPaymentService.class);

        Call<List<PaymentBank>> call = service.getPaymentBanks(getString(R.string.mp_public_key), "visa");
        call.enqueue(new Callback<List<PaymentBank>>()
        {
            @Override
            public void onResponse(Call<List<PaymentBank>> call, Response<List<PaymentBank>> response)
            {
                setPaymentBankRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<PaymentBank>> call, Throwable t)
            {

            }
        });
    }

    private void setPaymentBankRecyclerView(List<PaymentBank> paymentBankList)
    {
        RecyclerView methodsRecyclerView = mBinding.bankRv;

        methodsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        methodsRecyclerView.setAdapter(new PaymentBankRecyclerAdapter(paymentBankList));
    }

    @Override
    public int getFragmentId()
    {
        return MainActivity.FRAGMENT_BANK;
    }
}
