package com.example.dan.paymentapp.network;

import com.example.dan.paymentapp.models.PaymentBank;
import com.example.dan.paymentapp.models.PaymentIssuer;
import com.example.dan.paymentapp.models.PaymentMethod;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MPPaymentService
{
    @GET("payment_methods")
    Call<List<PaymentMethod>> getPaymentMethods(@Query("public_key") String publicKey);

    @GET("payment_methods/card_issuers")
    Call<List<PaymentBank>> getPaymentBanks(@Query("public_key") String publicKey, @Query("payment_method_id") String paymentMethodId);

    @GET("payment_methods/installments")
    Call<List<PaymentIssuer>> getPaymentIssuerQuotasInfo(@Query("public_key") String publicKey,
                                                        @Query("payment_method_id") String paymentMethodId,
                                                        @Query("amount") int amount,
                                                        @Query("issuer.id") int issuerId);
}
