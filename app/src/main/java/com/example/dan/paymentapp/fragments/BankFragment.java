package com.example.dan.paymentapp.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.MainActivity;
import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.adapters.BankRecyclerClickListener;
import com.example.dan.paymentapp.adapters.PaymentBankRecyclerAdapter;
import com.example.dan.paymentapp.databinding.FragmentBankBinding;
import com.example.dan.paymentapp.models.bind.GeneralBindModel;
import com.example.dan.paymentapp.models.viewmodels.BankViewModel;
import com.example.dan.paymentapp.models.viewmodels.MPDataViewModel;
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
public class BankFragment extends BaseFragment implements BankRecyclerClickListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentBankBinding mBinding;

    private BankViewModel mBankViewModel;
    private MPDataViewModel mMPDataViewModel;

    private GeneralBindModel mBindModel;

    public BankFragment()
    {
        // Required empty public constructor
    }

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

        mBindModel = new GeneralBindModel();
        mMPDataViewModel = ViewModelProviders.of(getActivity()).get(MPDataViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mBinding = FragmentBankBinding.inflate(inflater, container, false);

        mBinding.setId(getFragmentId());
        mBinding.setBindModel(mBindModel);

        mBankViewModel = ViewModelProviders.of(this).get(BankViewModel.class);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        if(mBankViewModel.getPaymentBankList() == null)
            getPaymentBanks();
        else
        {
            setPaymentBankRecyclerView(mBankViewModel.getPaymentBankList());
            mBindModel.isLoading.set(false);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }

    private void getPaymentBanks()
    {
        // Show only progress bar and hide all other views
        mBindModel.isLoading.set(true);

        MPPaymentService service = RetrofitClient.getRetrofitInstance().create(MPPaymentService.class);

        Call<List<PaymentBank>> call = service.getPaymentBanks(getString(R.string.mp_public_key), mMPDataViewModel.paymentMethod.get());
        call.enqueue(new Callback<List<PaymentBank>>()
        {
            @Override
            public void onResponse(Call<List<PaymentBank>> call, Response<List<PaymentBank>> response)
            {
                if(response.body() == null)
                    return;

                // Check if user previuosly select a bank
                PaymentBank selectedBank = mMPDataViewModel.getBank();

                if(selectedBank != null)
                    checkPreviouslySelectedBank(selectedBank.getId(), response.body());

                setPaymentBankRecyclerView(response.body());

                mBindModel.isLoading.set(false);
            }

            @Override
            public void onFailure(Call<List<PaymentBank>> call, Throwable t)
            {
                mBindModel.isLoading.set(false);
            }
        });
    }

    private void checkPreviouslySelectedBank(int bankId, List<PaymentBank> bankList)
    {
        for (PaymentBank bank : bankList)
            if(bank.getId() == bankId)
                bank.isSelected.set(true);
    }

    private void setPaymentBankRecyclerView(List<PaymentBank> paymentBankList)
    {
        RecyclerView methodsRecyclerView = mBinding.bankRv;

        methodsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        methodsRecyclerView.setAdapter(new PaymentBankRecyclerAdapter(paymentBankList, this));
    }

    @Override
    public void onBankClick(PaymentBank bank)
    {
        mMPDataViewModel.paymentBank.set(bank.getId());
        mMPDataViewModel.setBank(bank);
    }

    @Override
    public int getFragmentId()
    {
        return MainActivity.FRAGMENT_BANK;
    }
}
