package com.example.dan.paymentapp.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.FragmentClicksListener;
import com.example.dan.paymentapp.MainActivity;
import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.databinding.FragmentMethodBinding;

/**
 * To register the payment method desired for the user
 */
public class MethodFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentClicksListener mListener;

    public MethodFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MethodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MethodFragment newInstance(String param1, String param2)
    {
        MethodFragment fragment = new MethodFragment();
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
        FragmentMethodBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_method, container, false);

        binding.setId(MainActivity.FRAGMENT_METHOD);

        return binding.getRoot();
    }

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
