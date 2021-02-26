package com.example.tracer.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClientInstance {

    private static Retrofit geminiRetrofit;
    private static Retrofit binanceRetrofit;
    private static final String BASE_URL = "https://api.gemini.com/v2/";

    public static Retrofit getRetrofitInstance(String Exchange) {
        Retrofit retrofit = null;
        if (Exchange.equals("Gemini")) {
            if (geminiRetrofit == null) {
                geminiRetrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }
            retrofit = geminiRetrofit;
        } else if (Exchange.equals("Binance")){
            if (binanceRetrofit == null) {
                binanceRetrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }
            retrofit = binanceRetrofit;
        }
        return retrofit;
    }
}
