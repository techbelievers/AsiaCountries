package com.asia.Country.database;



import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.asia.Country.model.Country;


@Database(entities = {Country.class}, version = 2)
public abstract class CountryRoomDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();

    private static CountryRoomDatabase INSTANCE;

    public static CountryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CountryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CountryRoomDatabase.class, "country_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static Callback sRoomDatabaseCallback = new Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CountryDao mDao;

        PopulateDbAsync(CountryRoomDatabase db) {
            mDao = db.countryDao();
        }



        @Override
        protected Void doInBackground(final Void... params) {

           // mDao.deleteAll();
          //  Country country = new Country("INDIA", "Mumbai", "abcd", "bhdhdh" , 123 , "shhshshs" , "hjsjs" , "hhh");
           try{
             //  mDao.insert(country);
           }catch (Exception e)
           {
               return  null;
           }
            return null;
        }
    }

}
