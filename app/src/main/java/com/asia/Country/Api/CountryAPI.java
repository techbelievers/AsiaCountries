package com.asia.Country.Api;

import android.util.Log;

import com.asia.Country.model.CountryList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class CountryAPI {



    private static final String region = "Asia";
    private static final String url ="https://restcountries.eu/rest/v2/region/";

    public  static CountryService countryService =null;

    public static CountryService getService(){
        if (countryService==null)
        {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.d("TAG", retrofit.toString());
            countryService = retrofit.create(CountryService.class);
        }
        return countryService;
    }

    public interface CountryService
    {

        @Headers("Content-Type: application/json")
        @GET("Asia")
        Call<List<CountryList>> getCountryList();

    }

}
