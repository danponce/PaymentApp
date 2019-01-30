package com.example.dan.paymentapp.network;

import com.example.dan.paymentapp.PaymentMethod;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface MPPaymentService
{
    @GET()
    Call<List<PaymentMethod>> getPaymentMethods(@Query("public_key") String publicKey);
}
