package com.asia.Country.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.asia.Country.model.Country;

import java.util.List;



@Dao
public interface CountryDao {


    @Query("SELECT * from country_table ORDER BY name ASC")
    LiveData<List<Country>> getAlphabetizedCountry();


    @Insert
    void insert(Country country);

    @Query("DELETE FROM country_table")
    void deleteAll();
}
