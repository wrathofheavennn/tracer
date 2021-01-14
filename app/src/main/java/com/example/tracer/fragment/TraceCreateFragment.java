package com.example.tracer.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tracer.R;
import com.example.tracer.utils.Keyboard;
import com.example.tracer.viewModel.TradeViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.spark.submitbutton.SubmitButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Juned on 11/19/2017.
 */

public class TraceCreateFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.submitBtn)
    SubmitButton submitBtn;
    @BindView(R.id.priceInput)
    TextInputEditText priceInput;
    @BindView(R.id.amountInput)
    TextInputEditText amountInput;
    @BindView(R.id.spinner)
    Spinner spin;

    String cryptoSelected = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TradeViewModel tradeViewModel = ViewModelProviders.of(this)
                .get(TradeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_create_trace, container, false);
        ButterKnife.bind(this, view);

        spin.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("BTC");
        categories.add("BCH");
        categories.add("ETH");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spin.setAdapter(dataAdapter);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid() && !cryptoSelected.equals("")) {
                    Keyboard.closeKeyboard(view, getContext());
                    tradeViewModel.addTrade(cryptoSelected, Double.parseDouble(priceInput.getText()
                            .toString()), 0, Double.parseDouble(amountInput.getText().toString()));
                    NavHostFragment.findNavController(Objects.requireNonNull(
                            getParentFragment())).popBackStack();
                } else {
                    Snackbar sb = Snackbar.make(view, "Please input correct prices!", Snackbar.LENGTH_LONG);
                    sb.setActionTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
                    sb.show();
                }
            }
        });
        return view;
    }

    private boolean isValid() {
        Boolean isValid = false;
        Double priceCheck;
        Double amountCheck;
        try {
            priceCheck = Double.parseDouble(priceInput.getText().toString());
            amountCheck = Double.parseDouble(amountInput.getText().toString());
            isValid = true;
        } catch (Exception e) {
            Timber.e(e);
        }
        return isValid;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Create new Trace ");
    }

    @Override
    //Performing action onItemSelected and onNothing selected
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        cryptoSelected = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}