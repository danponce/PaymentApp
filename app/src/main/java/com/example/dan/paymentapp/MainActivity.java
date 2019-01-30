package com.example.dan.paymentapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    public void previousFragment()
    {

    }

    @Override
    public void nextFragment()
    {

    }

    private Fragment getInitialFragment()
    {
        return AmountFragment.newInstance("", "");
    }
}
