package com.example.tracer.model;

import java.util.ArrayList;

public class Trade {
    private String cryptoName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public long getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(long amountBought) {
        this.amountBought = amountBought;
    }

    private long buyPrice;
    private long sellPrice;
    private long amountBought;

    public Trade(String cryptoName, long buyPrice, long sellPrice, long amountBought) {
        this.id = System.currentTimeMillis();
        this.cryptoName = cryptoName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.amountBought = amountBought;
    }

}
