package com.example.dan.paymentapp.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.databinding.FragmentInitSummaryBinding;
import com.example.dan.paymentapp.models.viewmodels.MPDataViewModel;

/**
 * Used for initiate the payment
 * functionality and to show the
 * summary of the selected info
 * by the user
 */
public class InitSummaryFragment extends Fragment
{
    private OnStartProcessListener mListener;

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

        mMPDataViewModel = ViewModelProviders.of(getActivity()).get(MPDataViewModel.class);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnStartProcessListener)
        {
            mListener = (OnStartProcessListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnStartProcessListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        FragmentInitSummaryBinding binding = FragmentInitSummaryBinding.inflate(inflater, container, false);

        binding.setModel(mMPDataViewModel);
        binding.nextContainer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mListener.onStartClick();
            }
        });

        return binding.getRoot();
    }

    public interface OnStartProcessListener
    {
        void onStartClick();
    }

}
