package com.example.dan.paymentapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.paymentapp.FragmentClicksListener;
import com.example.dan.paymentapp.MainActivity;
import com.example.dan.paymentapp.adapters.MethodRecyclerClickListener;
import com.example.dan.paymentapp.models.bind.GeneralBindModel;
import com.example.dan.paymentapp.models.viewmodels.MPDataViewModel;
import com.example.dan.paymentapp.models.viewmodels.MethodViewModel;
import com.example.dan.paymentapp.models.PaymentMethod;
import com.example.dan.paymentapp.R;
import com.example.dan.paymentapp.adapters.PaymentMethodRecyclerAdapter;
import com.example.dan.paymentapp.databinding.FragmentMethodBinding;
import com.example.dan.paymentapp.network.MPPaymentService;
import com.example.dan.paymentapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * To register the payment method desired for the user
 */
public class MethodFragment extends BaseFragment implements MethodRecyclerClickListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentClicksListener mListener;

    private FragmentMethodBinding mBinding;

    private MethodViewModel mMethodViewModel;
    private MPDataViewModel mMPDataViewModel;

    private GeneralBindModel mBindModel;

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

        mBindModel = new GeneralBindModel();

        mMPDataViewModel = ViewModelProviders.of(getActivity()).get(MPDataViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_method, container, false);

        mBinding.setId(MainActivity.FRAGMENT_METHOD);
        mBinding.setBindModel(mBindModel);

        mMethodViewModel = ViewModelProviders.of(this).get(MethodViewModel.class);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        if(mMethodViewModel.getPaymentMethodList() == null)
            getPaymentMethods();
        else
        {
            setPaymentMethodRecyclerView(mMethodViewModel.getPaymentMethodList());

            mBindModel.isLoading.set(false);
        }
    }

    private void getPaymentMethods()
    {
        // Show progress bar instead all other layout elements
        mBindModel.isLoading.set(true);

        MPPaymentService service = RetrofitClient.getRetrofitInstance().create(MPPaymentService.class);

        Call<List<PaymentMethod>> call = service.getPaymentMethods(getString(R.string.mp_public_key));
        call.enqueue(new Callback<List<PaymentMethod>>()
        {
            @Override
            public void onResponse(Call<List<PaymentMethod>> call, Response<List<PaymentMethod>> response)
            {
                if(response.body() == null)
                    return;

                // Check if user previously selected a method
                PaymentMethod selectedMethod = mMPDataViewModel.getMethod();

                if(selectedMethod != null)
                    checkSelectedMethod(selectedMethod.getId(), response.body());

                setPaymentMethodRecyclerView(response.body());

                mBindModel.isLoading.set(false);
            }

            @Override
            public void onFailure(Call<List<PaymentMethod>> call, Throwable t)
            {
                mBindModel.isLoading.set(false);
            }
        });
    }

    private void checkSelectedMethod(String selectedMethod, List<PaymentMethod> methodList)
    {
        for (PaymentMethod method : methodList)
            if(method.getId().equalsIgnoreCase(selectedMethod))
                method.isSelected.set(true);
    }

    @Override
    public void onMethodClick(PaymentMethod method)
    {
        mMPDataViewModel.paymentMethod.set(method.getId());
        mMPDataViewModel.setMethod(method);
    }

    private void setPaymentMethodRecyclerView(List<PaymentMethod> paymentMethodList)
    {
        RecyclerView methodsRecyclerView = mBinding.methodRv;

        methodsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        methodsRecyclerView.setAdapter(new PaymentMethodRecyclerAdapter(paymentMethodList, this));
    }

    @Override
    public int getFragmentId()
    {
        return MainActivity.FRAGMENT_METHOD;
    }
}
