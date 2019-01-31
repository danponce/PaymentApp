package com.example.dan.paymentapp.network;

import com.example.dan.paymentapp.models.PaymentMethod;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MPPaymentService
{
    @GET("payment_methods")
    Call<List<PaymentMethod>> getPaymentMethods(@Query("public_key") String publicKey);
}
