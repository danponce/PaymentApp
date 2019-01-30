package com.example.dan.paymentapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dan.paymentapp.fragments.AmountFragment;
import com.example.dan.paymentapp.fragments.MethodFragment;

public class MainActivity extends AppCompatActivity implements FragmentClicksListener
{
    public static final int FRAGMENT_AMOUNT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialFragment();
    }

    private void setInitialFragment()
    {
        // Get the fragment manager
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container, getInitialFragment());
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void previousFragment(int fragmentId)
    {

    }

    @Override
    public void nextFragment(int fragmentId)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container, getNextFragment(fragmentId));
        fragmentTransaction.commitAllowingStateLoss();
    }

    private Fragment getNextFragment(int fragmentId)
    {
        switch (fragmentId)
        {
            case FRAGMENT_AMOUNT : return MethodFragment.newInstance("", "");

            default: return null;
        }
    }

    private Fragment getInitialFragment()
    {
        return AmountFragment.newInstance("", "");
    }
}
