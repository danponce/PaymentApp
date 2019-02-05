package com.example.dan.paymentapp;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dan.paymentapp.fragments.AmountFragment;
import com.example.dan.paymentapp.fragments.BankFragment;
import com.example.dan.paymentapp.fragments.InitSummaryFragment;
import com.example.dan.paymentapp.fragments.IssuerQuotasFragment;
import com.example.dan.paymentapp.fragments.MethodFragment;
import com.example.dan.paymentapp.models.viewmodels.MPDataViewModel;
import com.example.dan.paymentapp.utils.GeneralUtils;
import com.example.dan.paymentapp.utils.ToastUtils;

public class MainActivity extends AppCompatActivity implements FragmentClicksListener, InitSummaryFragment.OnStartProcessListener
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
    public void previousFragment(int fragmentId, View sharedTransitionView)
    {
        replaceFragment(getPreviousFragment(fragmentId), sharedTransitionView);
    }

    @Override
    public void nextFragment(int fragmentId, View sharedTransitionView)
    {
        if(!shouldGoToNextFragment(fragmentId))
            return;

        replaceFragment(getNextFragment(fragmentId), sharedTransitionView);
    }

    private void replaceFragment(Fragment fragment, View sharedTransitionView)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if(fragment != null)
        {
            fragmentTransaction.replace(R.id.container, fragment);

            // Check if there's a shared transition to do
            if(sharedTransitionView != null)
                fragmentTransaction.addSharedElement(sharedTransitionView, getString(R.string.shared_transition_name));

            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    private Fragment getNextFragment(int fragmentId)
    {
        switch (fragmentId)
        {
            case FRAGMENT_AMOUNT :

                // hide the keyboard if it's opened
                GeneralUtils.hideKeyboard(this);

                return MethodFragment.newInstance();

            case FRAGMENT_METHOD : return BankFragment.newInstance();
            
            case FRAGMENT_BANK : return IssuerQuotasFragment.newInstance();

            case FRAGMENT_ISSUER_QUOTAS :

                // Also we finish the process
                mMPDataViewModel.isFinished.set(true);

                return InitSummaryFragment.newInstance();

            default: return null;
        }
    }

    /**
     * Validates if the user selected
     * or add the necessary info based
     * on the corresponding fragment
     * @param fragmentId    the corresponding fragment id
     * @return              false if it shouldn't go to next fragment, true otherwise
     */
    private boolean shouldGoToNextFragment(int fragmentId)
    {
        switch (fragmentId)
        {
            case FRAGMENT_AMOUNT :
                if(mMPDataViewModel.paymentAmmount.get() <= 0)
                {
                    ToastUtils.showShortToast(this, R.string.invalid_amount);

                    return false;
                }

                break;

            case FRAGMENT_METHOD :
                if(mMPDataViewModel.getMethod() == null)
                {
                    ToastUtils.showShortToast(this, R.string.invalid_method);

                    return false;
                }

                break;

            case FRAGMENT_BANK :
                if(mMPDataViewModel.getBank() == null)
                {
                    ToastUtils.showShortToast(this, R.string.invalid_bank);

                    return false;
                }

                break;
        }

        return true;
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

    @Override
    public void onStartClick()
    {
        if(mMPDataViewModel.isFinished.get())
            // Set a new instance of the MPDataViewModel
            mMPDataViewModel.reset();

        replaceFragment(AmountFragment.newInstance(), null);
    }
}
