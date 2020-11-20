package com.asia.Country.Adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asia.Country.R;
import com.asia.Country.model.Country;
import com.asia.Country.utils.Utils;

import java.util.List;


public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {


    //name, capital, flag(display image in app), region, subregion,
    //population, borders & languages.

    Activity context;
    class CountryViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, capital, region , subegion , population , borders , languages;
        private  final ImageView flag;

        private CountryViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
            subegion = itemView.findViewById(R.id.subregion);
            population = itemView.findViewById(R.id.population);
            borders = itemView.findViewById(R.id.borders);
            languages = itemView.findViewById(R.id.languages);
            flag  = itemView.findViewById(R.id.flag);
        }
    }

    private final LayoutInflater mInflater;
    private List<Country> mCountry; // Cached copy of words

    public CountryListAdapter(Activity context) {
        this.context = context;
        mInflater = LayoutInflater.from(context); }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.country_item, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        Country current = mCountry.get(position);
        holder.name.setText(current.getCountryName());
        holder.capital.setText(current.getCountryCapital());
        holder.region.setText(current.getCountryRegion());
        holder.subegion.setText(current.getCountrySubregion());
        holder.population.setText(current.getCountryPopulation().toString());
        holder.languages.setText(current.getCountryLanguages());
        holder.borders.setText(current.getCountryBorders());
        setFlag(current.getCountryFlag() , holder.flag);
    }

    private void setFlag(String countryFlag, ImageView flag) {


        Utils.fetchSvg(context, countryFlag, flag);

//      SvgLoader.pluck()
//                .with(context)
//                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
//                .load(countryFlag, flag);

       // Tools.displayImageOriginal(context , flag , countryFlag );
      //  Glide.with(context).load("https://restcountries.eu/data/afg.svg").into(flag);
        Log.d("TAG", "setFlag: "+ countryFlag);
    }

    public void setCountry(List<Country> countries){
        mCountry = countries;
        notifyDataSetChanged();
    }

    public void clearAll()
    {
        mCountry.clear();
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCountry != null)
            return mCountry.size();
        else return 0;
    }
}


