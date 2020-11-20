package com.asia.Country.Adapter.viewmodel;



import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.asia.Country.Repository.CountryRepository;
import com.asia.Country.model.Country;

import java.util.List;



public class CountryViewModel extends AndroidViewModel {

    private CountryRepository mRepository;

    private LiveData<List<Country>> mAllCountry;

    public CountryViewModel(Application application) {
        super(application);
        mRepository = new CountryRepository(application);
        mAllCountry = mRepository.getAllCountry();
    }

    public LiveData<List<Country>> getAllCountry() { return mAllCountry; }

    public void insert(Country country) { mRepository.insert(country); }

    public  void deleteAll(){

    }
}