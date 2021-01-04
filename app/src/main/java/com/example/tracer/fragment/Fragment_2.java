package com.example.tracer.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tracer.R;
import com.example.tracer.apiService.ApiService;
import com.example.tracer.model.TickerV2;
import com.example.tracer.model.Trade;
import com.example.tracer.network.RetroFitClientInstance;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Juned on 11/19/2017.
 */

public class Fragment_2 extends Fragment {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    @BindView(R.id.btcLastPrice)
    TextView price;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_2, container, false);
        ButterKnife.bind(this, view);
        loadRecyclerViewData();
        // Create the Handler object (on the main thread by default)
        handler = new Handler();
        // Start the initial runnable task by posting through the handler
        handler.post(runnableCode);
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        //getActivity().getActionBar().hide();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Fragment_2_Gallery ");
    }
    private float lastPrice = 0 ;
    private void loadRecyclerViewData() {
        String symbol = "BTCUSD";
        ApiService apiService = RetroFitClientInstance.getRetrofitInstance()
                .create(ApiService.class);
        // make a request by calling the corresponding method
        Single<TickerV2> symbolData = apiService.getSymbolData(symbol);
        symbolData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TickerV2>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(TickerV2 symbolData) {
                        compareLast(symbolData);
                        String strDouble = String.format("%.2f", lastPrice);
                        price.setText(strDouble);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Timber.d("ivan error: " + e.getMessage());
                    }
                });
    }

    private void compareLast(TickerV2 symbolData) {
        if (lastPrice > symbolData.getAsk()) {
            price.setTextColor(Color.RED);
        } else if (lastPrice < symbolData.getAsk()) {
            price.setTextColor(Color.GREEN);
        }
        lastPrice = symbolData.getAsk();
    }

    // Define the code block to be executed
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            loadRecyclerViewData();
            // Repeat this the same runnable code block again another 2 seconds
            // 'this' is referencing the Runnable object
            handler.postDelayed(this, 5000);
        }
    };

}