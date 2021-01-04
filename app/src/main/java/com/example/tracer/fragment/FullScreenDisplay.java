package com.example.tracer.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
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
import java.util.ArrayList;
import java.util.List;

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

public class FullScreenDisplay extends Fragment implements AdapterView.OnItemSelectedListener {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    @BindView(R.id.btcLastPrice)
    TextView price;
    @BindView(R.id.buyAskSwitch)
    Switch buyAskSwitch;
    @BindView(R.id.fullScreenSpinner)
    Spinner spin;

    String cryptoSelected = "";

    Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_2, container, false);
        ButterKnife.bind(this, view);
        loadFullScreenData();

        cryptoSelected = "BTCUSD";
        spin.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("BTCUSD");
        categories.add("BCHUSD");
        categories.add("ETHUSD");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spin.setAdapter(dataAdapter);

        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleControls();
            }
        });

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

    private void toggleControls() {
        if (spin.getVisibility() == View.VISIBLE) {
            spin.setVisibility(View.INVISIBLE);
            buyAskSwitch.setVisibility(View.INVISIBLE);
        } else {
            spin.setVisibility(View.VISIBLE);
            buyAskSwitch.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Full Screen Price");
    }
    private float lastPrice = 0 ;
    private void loadFullScreenData() {
        ApiService apiService = RetroFitClientInstance.getRetrofitInstance()
                .create(ApiService.class);
        // make a request by calling the corresponding method
        Single<TickerV2> symbolData = apiService.getSymbolData(cryptoSelected);
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
        Float bidOrAsk;
        if (buyAskSwitch.isChecked()) {
            bidOrAsk = symbolData.getAsk();
        } else {
            bidOrAsk = symbolData.getBid();
        }
        if (lastPrice > bidOrAsk) {
            price.setTextColor(Color.RED);
        } else {
            price.setTextColor(Color.GREEN);
        }
        lastPrice = bidOrAsk;
    }

    // Define the code block to be executed
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            loadFullScreenData();
            // Repeat this the same runnable code block again another 2 seconds
            // 'this' is referencing the Runnable object
            handler.postDelayed(this, 5000);
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cryptoSelected = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}