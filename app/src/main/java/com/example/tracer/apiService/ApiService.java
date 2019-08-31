package com.example.tracer.apiService;

import com.example.tracer.model.TickerV2;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("ticker/{symbol}")
    Single<TickerV2> getSymbolData(@Path("symbol") String symbol);
}
