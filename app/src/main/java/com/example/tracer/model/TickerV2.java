package com.example.tracer.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TickerV2 {

    @Expose
    @SerializedName("symbol")
    private String symbol;
    @Expose
    @SerializedName("open")
    private float open;
    @Expose
    @SerializedName("high")
    private float high;
    @Expose
    @SerializedName("low")
    private float low;
    @Expose
    @SerializedName("close")
    private float close;
    @Expose
    @SerializedName("changes")
    private ArrayList<Float> changes;
    @Expose
    @SerializedName("bid")
    private float bid;
    @Expose
    @SerializedName("ask")
    private float ask;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public ArrayList<Float> getChanges() {
        return changes;
    }

    public void setChanges(ArrayList<Float> changes) {
        this.changes = changes;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

}
