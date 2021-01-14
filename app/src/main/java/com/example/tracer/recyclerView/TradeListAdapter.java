package com.example.tracer.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracer.R;
import com.example.tracer.model.Trade;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TradeListAdapter extends
        RecyclerView.Adapter<TradeListAdapter.ViewHolder> {


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        @BindView(R.id.buyAmount)
        TextView buyAmount;
        @BindView(R.id.buyPrice)
        TextView buyPrice;
        @BindView(R.id.askPrice)
        TextView askPrice;
        @BindView(R.id.pnlPercent)
        TextView pnlPercent;
        @BindView(R.id.pnlActual)
        TextView pnlActual;
        @BindView(R.id.cryptoImage)
        ImageView cryptoImage;
        @BindView(R.id.buyAmountSize)
        TextView buyAmountSize;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private List<Trade> tradeList;
    // Pass in the contact array into the constructor
    public TradeListAdapter(List<Trade> tradeList) {
        this.tradeList = tradeList;
    }
    @Override
    public TradeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View tradeView = inflater.inflate(R.layout.rv_trade_layout, parent, false);

        // Return a new holder instance
        return new ViewHolder(tradeView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TradeListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Trade trade = tradeList.get(position);

        // Set item views based on your views and data model
        if (trade.getCryptoName().equalsIgnoreCase("btc")) {
            viewHolder.cryptoImage.setImageResource(R.drawable.ic_icons8_bitcoin);
        } else if (trade.getCryptoName().equalsIgnoreCase("bch")) {
            viewHolder.cryptoImage.setImageResource(R.drawable.ic_icons8_bitcoingreen);
        } else if (trade.getCryptoName().equalsIgnoreCase("eth")) {
            viewHolder.cryptoImage.setImageResource(R.drawable.ic_icons8_ethereum);
        }

        //truncate to 2 decimals
        String pnlPercentDouble = String.format("%.2f", trade.getProfitLossPercent());
        String pnlActualDouble = String.format("%.2f", trade.getProfitLossActual());
        String askPriceText = String.format("%.2f", trade.getAskPrice());
        String buyPriceText = String.format("%.2f", trade.getBuyPrice());
        String buyAmountText = String.format("%.2f", trade.getAmountBought());
        double size = trade.getAmountBought() / trade.getBuyPrice();
        String buyAmountSizeText = String.format("%.9f", size);

        viewHolder.buyAmount.setText(buyAmountText);
        viewHolder.buyPrice.setText(buyPriceText);
        viewHolder.askPrice.setText(askPriceText);
        viewHolder.pnlPercent.setText(pnlPercentDouble);
        viewHolder.pnlActual.setText(pnlActualDouble);
        viewHolder.buyAmountSize.setText(buyAmountSizeText);
        double x = trade.getProfitLossPercent();
        Context context = viewHolder.pnlActual.getContext();
        if (x>0) {
            viewHolder.pnlPercent.setTextColor(ContextCompat.getColor(context,R.color.colorGreen));
            viewHolder.pnlActual.setTextColor(ContextCompat.getColor(context,R.color.colorGreen));
        } else if (x<0) {
            viewHolder.pnlPercent.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
            viewHolder.pnlActual.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return tradeList.size();
    }

    public List<Trade> getList() {
        return tradeList;
    }

    public void removeItem(int position) {
        tradeList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Trade item, int position) {
        tradeList.add(position, item);
        notifyItemInserted(position);
    }
}
