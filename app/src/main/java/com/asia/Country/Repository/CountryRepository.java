package com.asia.Country.Repository;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.asia.Country.database.CountryRoomDatabase;
import com.asia.Country.model.Country;
import com.asia.Country.database.CountryDao;

import java.util.List;



public class CountryRepository {

    private CountryDao mCountryDao;
    private LiveData<List<Country>> mAllCountry;


    public CountryRepository(Application application) {
       CountryRoomDatabase db = CountryRoomDatabase.getDatabase(application);
        mCountryDao = db.countryDao();
        mAllCountry = mCountryDao.getAlphabetizedCountry();
    }


    public LiveData<List<Country>> getAllCountry() {
        return mAllCountry;
    }


    public void insert (Country country) {
        new insertAsyncTask(mCountryDao).execute(country);
    }

    public void deleteAll(){

    }

    private static class insertAsyncTask extends AsyncTask<Country, Void, Void> {

        private CountryDao mAsyncTaskDao;

        insertAsyncTask(CountryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Country... params) {

            try {
                mAsyncTaskDao.insert(params[0]);
            }
            catch (Exception e)
            {
                return  null;
            }
            return null;
        }
    }
}
