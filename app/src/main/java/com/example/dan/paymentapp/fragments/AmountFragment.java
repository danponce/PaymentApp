package com.example.dan.paymentapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.transition.TransitionInflater;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.FragmentClicksListener;
import com.example.dan.paymentapp.MainActivity;
import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.databinding.FragmentAmountBinding;
import com.example.dan.paymentapp.models.viewmodels.MPDataViewModel;


/**
 * For setting the payment amount desired by the user
 */
public class AmountFragment extends BaseFragment
{
    private FragmentAmountBinding mBinding;

    private MPDataViewModel mMPDataViewModel;

    public AmountFragment()
    {
        // Required empty public constructor
    }

    public static AmountFragment newInstance()
    {
        AmountFragment fragment = new AmountFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mMPDataViewModel = ViewModelProviders.of(getActivity()).get(MPDataViewModel.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_amount, container, false);
        mBinding.setId(getFragmentId());
        mBinding.setAmount(mMPDataViewModel.paymentAmmount);

        setClickListeners();

        return mBinding.getRoot();
    }

    private void setClickListeners()
    {

    }

    @Override
    public int getFragmentId()
    {
        return MainActivity.FRAGMENT_AMOUNT;
    }

    @Override
    public View getSharedTransitionView()
    {
        return mBinding.stepIv;
    }
}
