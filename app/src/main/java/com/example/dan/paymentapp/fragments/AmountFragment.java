package com.example.dan.paymentapp.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.FragmentClicksListener;
import com.example.dan.paymentapp.MainActivity;
import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.databinding.FragmentAmountBinding;


/**
 * For setting the payment amount desired by the user
 */
public class AmountFragment extends BaseFragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentClicksListener mListener;

    private FragmentAmountBinding mBinding;

    public AmountFragment()
    {
        // Required empty public constructor
    }

    public static AmountFragment newInstance(String param1, String param2)
    {
        AmountFragment fragment = new AmountFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_amount, container, false);
        mBinding.setId(getFragmentId());

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
}
