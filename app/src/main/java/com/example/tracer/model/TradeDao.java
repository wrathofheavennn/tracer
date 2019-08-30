package com.example.tracer.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TradeDao {
    @Query("Select * from trade order by id")
    LiveData<List<Trade>> getTradesList();
    @Insert
    void insertTrade(Trade trade);
    @Update
    void updatePerson(Trade trade);
    @Delete
    void deleteTrade(Trade trade);

}
