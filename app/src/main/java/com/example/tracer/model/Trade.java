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
    private double buyPrice;
    @ColumnInfo(name = "sellPrice")
    private double sellPrice;
    @ColumnInfo(name = "amountBought")
    private double amountBought;
    @ColumnInfo(name = "askPrice")
    private double askPrice;
    @ColumnInfo(name = "profitLossActual")
    private double profitLossActual;
    @ColumnInfo(name = "profitLossPercent")
    private double profitLossPercent;

    public double getProfitLossActual() {
        return profitLossActual;
    }

    public void setProfitLossActual(double profitLoss) {
        this.profitLossActual = profitLoss;
    }

    public double getProfitLossPercent() {
        return profitLossPercent;
    }

    public void setProfitLossPercent(double profitLossPercent) {
        this.profitLossPercent = profitLossPercent;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
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

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(double amountBought) {
        this.amountBought = amountBought;
    }

    public Trade(String cryptoName, double buyPrice, double sellPrice, double amountBought) {
        this.id = System.currentTimeMillis();
        this.cryptoName = cryptoName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.amountBought = amountBought;
    }

}
