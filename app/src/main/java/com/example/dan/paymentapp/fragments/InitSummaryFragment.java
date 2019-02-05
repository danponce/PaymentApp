package com.example.dan.paymentapp.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.models.viewmodels.MPDataViewModel;

/**
 * Used for initiate the payment
 * functionality and to show the
 * summary of the selected info
 * by the user
 */
public class InitSummaryFragment extends Fragment
{

    private MPDataViewModel mMPDataViewModel;

    public InitSummaryFragment()
    {
        // Required empty public constructor
    }

    public static InitSummaryFragment newInstance()
    {
        InitSummaryFragment fragment = new InitSummaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
        }

        mMPDataViewModel = ViewModelProviders.of(getActivity()).get(MPDataViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_init_summary, container, false);
    }

}
