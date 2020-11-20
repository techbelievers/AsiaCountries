package com.asia.Country;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.asia.Country.Adapter.CountryListAdapter;
import com.asia.Country.Adapter.viewmodel.CountryViewModel;
import com.asia.Country.Api.CountryAPI;
import com.asia.Country.database.CountryDao;
import com.asia.Country.database.CountryRoomDatabase;
import com.asia.Country.model.Country;
import com.asia.Country.model.CountryList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private CountryViewModel mCountryViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
   //     final WordListAdapter adapter = new WordListAdapter(this);
        final CountryListAdapter adapter = new CountryListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCountryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

        getCountry();



        mCountryViewModel.getAllCountry().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(@Nullable final List<Country> words) {
                // Update the cached copy of the words in the adapter.
               adapter.setCountry(words);
            }
        });

        final CountryRoomDatabase db = CountryRoomDatabase.getDatabase(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final CountryDao mDao;

                adapter.clearAll();
                TextView emptmsg = findViewById(R.id.emptymsg);
                emptmsg.setVisibility(View.VISIBLE);
                new PopulateDbAsync(db).execute();
            }
        });
    }
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CountryDao mDao;

        PopulateDbAsync(CountryRoomDatabase db) {
            mDao = db.countryDao();
        }



        @Override
        protected Void doInBackground(final Void... params) {

            mDao.deleteAll();
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


    private  void getCountry()
    {
        Log.d("TAG", "GetCountry");
        Call<List<CountryList>> countryList = CountryAPI.getService().getCountryList();
        countryList.enqueue(new Callback<List<CountryList>>() {

            @Override
            public void onResponse(Call<List<CountryList>> call, Response<List<CountryList>> response) {
                List<CountryList> list=response.body();

                save(list);
                Log.d("TAG",list.get(1).getName());
                Toast.makeText(MainActivity.this ,"Sucuss" ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<CountryList>> call, Throwable t) {
                Toast.makeText(MainActivity.this ,"fail" ,Toast.LENGTH_SHORT).show();
                Log.d("failure", "onFailure: " + t.getMessage());
            }
        });
    }

    private void save(List<CountryList> list) {
        int i=0;
        while(i<list.size())
        {
            CountryList c=list.get(i);
            int x=0;
            String lan ="";
            while(x<c.getLanguages().size())
            {
                lan+=" "+c.getLanguages().get(x).getName();
                x++;
            }
            int y=0;
            String bor ="";
            while(y<c.getBorders().size())
            {
                bor+=" ,"+c.getBorders().get(y);
                y++;
            }
            Country country = new Country(c.getName() , c.getCapital(),c.getRegion(),c.getSubregion(),c.getPopulation(),  lan,c.getFlag() , bor);
                  //  data.getStringExtra(NewWordActivity.DEPARTMENT));
            mCountryViewModel.insert(country);
            i++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
