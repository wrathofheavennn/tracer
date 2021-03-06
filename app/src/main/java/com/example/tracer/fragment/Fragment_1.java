package com.example.tracer.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracer.R;
import com.example.tracer.model.Trade;
import com.example.tracer.model.TradeDao;
import com.example.tracer.recyclerView.SwipeToDeleteCallback;
import com.example.tracer.recyclerView.TradeListAdapter;
import com.example.tracer.viewModel.TradeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

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
    private TradeListAdapter adapter;

    //swipe rv
    ConstraintLayout constraintLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_layout_1, container, false);
        ButterKnife.bind(this, view);
        fab.setOnClickListener(view1 -> NavHostFragment.findNavController(Objects.requireNonNull(
                getParentFragment())).navigate(Fragment_1Directions.
                actionFragment1ToTraceCreateFragment()));

        initRecycler();

        //swipe
        constraintLayout = view.findViewById(R.id.constraintLayout);


        TradeViewModel tradeViewModel = ViewModelProviders.of(this)
                .get(TradeViewModel.class);
        enableSwipeToDeleteAndUndo(tradeViewModel);
        tradeViewModel.getTradeListLiveData().observe(this, new Observer<List<Trade>>() {
            @Override
            public void onChanged(@Nullable List<Trade> tradeList) {
                trades.clear();
                trades.addAll(tradeList);
                Timber.d("ivan: " + tradeList.size());
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void enableSwipeToDeleteAndUndo(TradeViewModel tradeViewModel) {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Trade item = adapter.getList().get(position);
                adapter.removeItem(position);
                tradeViewModel.deleteTrade(item);

                Snackbar snackbar = Snackbar
                        .make(constraintLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        adapter.restoreItem(item, position);
                        tradeViewModel.addTrade(item.getCryptoName(),item.getBuyPrice(),
                                item.getSellPrice(),item.getAmountBought());
                        rvTrades.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(rvTrades);
    }

    private void initRecycler() {
        // Initialize contacts
        List<Trade> staticList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Trade t = new Trade("BTC",9700, 10000, 500);
            t.setBuyPrice(t.getBuyPrice()+i);
            staticList.add(t);
        }
        trades = staticList;
        DividerItemDecoration itemDecor = new DividerItemDecoration(
                Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL);
        rvTrades.addItemDecoration(itemDecor);
        // Create adapter passing in the sample user data
        adapter = new TradeListAdapter(trades);

        // Attach the adapter to the recyclerview to populate items
        rvTrades.setAdapter(adapter);
        // Set layout manager to position the items
        rvTrades.setLayoutManager(new LinearLayoutManager(getContext()));

        // That's all!
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        Objects.requireNonNull(getActivity()).setTitle("All Traces");
    }

}

