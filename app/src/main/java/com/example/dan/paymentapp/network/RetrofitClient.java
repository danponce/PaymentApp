package com.example.dan.paymentapp.network;

import retrofit2.Retrofit;

public class RetrofitClient
{
    private static Retrofit sRetrofit;
    private static final String BASE_URL = "https://api.mercadopago.com/v1/payment_methods";

    public static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            sRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();

        }
        return sRetrofit;
    }
}
