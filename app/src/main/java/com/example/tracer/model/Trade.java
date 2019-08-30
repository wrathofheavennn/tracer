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
    private long buyPrice;
    @ColumnInfo(name = "sellPrice")
    private long sellPrice;
    @ColumnInfo(name = "amountBought")
    private long amountBought;

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

    public Trade(String cryptoName, long buyPrice, long sellPrice, long amountBought) {
        this.id = System.currentTimeMillis();
        this.cryptoName = cryptoName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.amountBought = amountBought;
    }

}
