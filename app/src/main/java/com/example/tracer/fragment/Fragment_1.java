package com.example.tracer.fragment;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.example.tracer.R;
import com.example.tracer.model.Trade;
import com.example.tracer.recyclerView.TradeListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Juned on 11/19/2017.
 */

public class Fragment_1 extends Fragment {

    @BindView(R.id.addBtn)
    FloatingActionButton fab;
    @BindView(R.id.rvTrades)
    RecyclerView rvTrades;
    //rv
    private List<Trade> trades;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_layout_1, container, false);
        ButterKnife.bind(this, view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(getParentFragment()).navigate(Fragment_1Directions.actionFragment1ToTraceCreateFragment());
            }
        });

        initRecycler();

        return view;
    }

    private void initRecycler() {
        // Initialize contacts
        List<Trade> staticList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Trade t = new Trade("BTC",9700, 10000, 500);
            t.setBuyPrice(t.getBuyPrice()+i);
            staticList.add(t);
        }
        trades = staticList;
        // Create adapter passing in the sample user data
        TradeListAdapter adapter = new TradeListAdapter(trades);
        // Attach the adapter to the recyclerview to populate items
        rvTrades.setAdapter(adapter);
        // Set layout manager to position the items
        rvTrades.setLayoutManager(new LinearLayoutManager(getContext()));
        // That's all!
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("All Traces");
    }

}

