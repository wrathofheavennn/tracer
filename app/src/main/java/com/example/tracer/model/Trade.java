package com.example.tracer.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trade")
public class Trade {

    @PrimaryKey(autoGenerate = false)
    private long id;
    @ColumnInfo(name = "name")
    private String cryptoName;
    @ColumnInfo(name = "buyPrice")
    private float buyPrice;
    @ColumnInfo(name = "sellPrice")
    private float sellPrice;
    @ColumnInfo(name = "amountBought")
    private float amountBought;
    @ColumnInfo(name = "askPrice")
    private float askPrice;
    @ColumnInfo(name = "profitLossActual")
    private float profitLossActual;
    @ColumnInfo(name = "profitLossPercent")
    private float profitLossPercent;

    public float getProfitLossActual() {
        return profitLossActual;
    }

    public void setProfitLossActual(float profitLoss) {
        this.profitLossActual = profitLoss;
    }

    public float getProfitLossPercent() {
        return profitLossPercent;
    }

    public void setProfitLossPercent(float profitLossPercent) {
        this.profitLossPercent = profitLossPercent;
    }

    public float getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(float askPrice) {
        this.askPrice = askPrice;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public float getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(float amountBought) {
        this.amountBought = amountBought;
    }

    public Trade(String cryptoName, float buyPrice, float sellPrice, float amountBought) {
        this.id = System.currentTimeMillis();
        this.cryptoName = cryptoName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.amountBought = amountBought;
    }

}
