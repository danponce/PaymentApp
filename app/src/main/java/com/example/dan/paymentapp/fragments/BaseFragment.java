package com.example.dan.paymentapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.dan.paymentapp.FragmentClicksListener;
import com.example.dan.paymentapp.R;

public abstract class BaseFragment extends Fragment
{
    private FragmentClicksListener mListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        View buttons = view.findViewById(R.id.buttons);

        buttons.findViewById(R.id.back_btn).setOnClickListener(new BackClickListener());
        buttons.findViewById(R.id.next_btn).setOnClickListener(new NextClickListener());
    }

    private class NextClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            mListener.nextFragment(getFragmentId());
        }
    }

    private class BackClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            mListener.previousFragment(getFragmentId());
        }
    }

    public abstract int getFragmentId();

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof FragmentClicksListener)
        {
            mListener = (FragmentClicksListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentClicksListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }
}
