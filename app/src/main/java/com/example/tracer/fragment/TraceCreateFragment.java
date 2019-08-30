package com.example.tracer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tracer.R;
import com.example.tracer.viewModel.TradeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.spark.submitbutton.SubmitButton;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Juned on 11/19/2017.
 */

public class TraceCreateFragment extends Fragment {

    @BindView(R.id.submitBtn)
    SubmitButton submitBtn;
    @BindView(R.id.priceInput)
    TextInputEditText priceInput;
    @BindView(R.id.amountInput)
    TextInputEditText amountInput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TradeViewModel tradeViewModel = ViewModelProviders.of(this)
                .get(TradeViewModel.class);

        View view = inflater.inflate(R.layout.fragment_create_trace, container, false);
        ButterKnife.bind(this, view);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    tradeViewModel.addTrade("BTC", Long.parseLong(priceInput.getText()
                            .toString()), 0, Long.parseLong(amountInput.getText().toString()));
                    NavHostFragment.findNavController(Objects.requireNonNull(
                            getParentFragment())).popBackStack();
                }
            }
        });
        return view;
    }

    private void initButton(TradeViewModel tv) {

    }

    private boolean isValid() {
        return true;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Create new Trace ");
    }

}