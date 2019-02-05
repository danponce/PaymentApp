package com.example.dan.paymentapp;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dan.paymentapp.fragments.AmountFragment;
import com.example.dan.paymentapp.fragments.BankFragment;
import com.example.dan.paymentapp.fragments.InitSummaryFragment;
import com.example.dan.paymentapp.fragments.IssuerQuotasFragment;
import com.example.dan.paymentapp.fragments.MethodFragment;
import com.example.dan.paymentapp.models.viewmodels.MPDataViewModel;

public class MainActivity extends AppCompatActivity implements FragmentClicksListener
{
    public static final int FRAGMENT_AMOUNT = 0;
    public static final int FRAGMENT_METHOD = 1;
    public static final int FRAGMENT_BANK = 2;
    public static final int FRAGMENT_ISSUER_QUOTAS = 3;

    private MPDataViewModel mMPDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMPDataViewModel = ViewModelProviders.of(this).get(MPDataViewModel.class);

        // If the activity is being created for the first time
        if(savedInstanceState == null)
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
        replaceFragment(getPreviousFragment(fragmentId));
    }

    @Override
    public void nextFragment(int fragmentId)
    {
        replaceFragment(getNextFragment(fragmentId));
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if(fragment != null)
        {
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    private Fragment getNextFragment(int fragmentId)
    {
        switch (fragmentId)
        {
            case FRAGMENT_AMOUNT : return MethodFragment.newInstance();

            case FRAGMENT_METHOD : return BankFragment.newInstance();
            
            case FRAGMENT_BANK : return IssuerQuotasFragment.newInstance();

            case FRAGMENT_ISSUER_QUOTAS : return InitSummaryFragment.newInstance();

            default: return null;
        }
    }

    private Fragment getPreviousFragment(int fragmentId)
    {
        switch (fragmentId)
        {
            case FRAGMENT_METHOD : return AmountFragment.newInstance();

            case FRAGMENT_BANK : return MethodFragment.newInstance();

            case FRAGMENT_ISSUER_QUOTAS : return BankFragment.newInstance();

            default: return null;
        }
    }

    private Fragment getInitialFragment()
    {
        return InitSummaryFragment.newInstance();
    }
}
