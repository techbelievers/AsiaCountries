package com.asia.Country.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

//name, capital, flag(display image in app), region, subregion,
//population, borders & languages.

@Entity(tableName = "country_table")
public class Country {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String countryName;

    @Nullable
    @ColumnInfo(name = "capital")
    private String countryCapital;

    @Nullable
    @ColumnInfo(name = "region")
    private String countryRegion;

    @Nullable
    @ColumnInfo(name = "subregion")
    private String countrySubregion;


    @Nullable
    @ColumnInfo(name = "population")
    private Integer countryPopulation;

    @Nullable
    @ColumnInfo(name = "borders")
    private String countryBorders;

    @NonNull
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(@NonNull String countryName) {
        this.countryName = countryName;
    }

    @Nullable
    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(@Nullable String countryCapital) {
        this.countryCapital = countryCapital;
    }

    @Nullable
    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(@Nullable String countryRegion) {
        this.countryRegion = countryRegion;
    }

    @Nullable
    public String getCountrySubregion() {
        return countrySubregion;
    }

    public void setCountrySubregion(@Nullable String countrySubregion) {
        this.countrySubregion = countrySubregion;
    }

    @Nullable
    public Integer getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(@Nullable Integer countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    @Nullable
    public String getCountryBorders() {
        return countryBorders;
    }

    public void setCountryBorders(@Nullable String countryBorders) {
        this.countryBorders = countryBorders;
    }

    @Nullable
    public String getCountryLanguages() {
        return countryLanguages;
    }

    public void setCountryLanguages(@Nullable String countryLanguages) {
        this.countryLanguages = countryLanguages;
    }

    @Nullable
    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(@Nullable String countryFlag) {
        this.countryFlag = countryFlag;
    }

    @Nullable
    @ColumnInfo(name = "languages")
    private String countryLanguages;

    @Nullable
    @ColumnInfo(name = "flag")
    private String countryFlag;

    public Country(@NonNull String countryName, @Nullable String countryCapital, @Nullable String countryRegion, @Nullable String countrySubregion ,@Nullable Integer countryPopulation  ,@Nullable String countryLanguages ,@Nullable String countryFlag ,@Nullable String countryBorders    ) {
        this.countryName = countryName;
        this.countryCapital = countryCapital;
        this.countryRegion = countryRegion;
        this.countrySubregion = countrySubregion;
        this.countryPopulation = countryPopulation;
       this.countryBorders = countryBorders;
        this.countryLanguages = countryLanguages;
        this.countryFlag = countryFlag;


    }


}