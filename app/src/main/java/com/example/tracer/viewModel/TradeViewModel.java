package com.example.tracer.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tracer.model.Trade;
import com.example.tracer.model.TradeDatabase;
import com.example.tracer.utils.AppExecutors;

import java.util.List;

public class TradeViewModel extends AndroidViewModel {
    private TradeDatabase tradeDatabase;
    private LiveData<List<Trade>> tradeListLiveData;

    public TradeViewModel(@NonNull Application application) {
        super(application);
        tradeDatabase = TradeDatabase.getInstance(application);
        tradeListLiveData = tradeDatabase.tradeDao().getTradesList();
    }
    public LiveData<List<Trade>> getTradeListLiveData() {
        return tradeListLiveData;
    }

    public void addTrade(String name, long buy, long sell, long amount) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Trade x = new Trade(name,buy,sell,amount);
                tradeDatabase.tradeDao().insertTrade(x);
            }
        });

    }

    public void deleteTrade(Trade t) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                tradeDatabase.tradeDao().deleteTrade(t);
            }
        });

    }
}
