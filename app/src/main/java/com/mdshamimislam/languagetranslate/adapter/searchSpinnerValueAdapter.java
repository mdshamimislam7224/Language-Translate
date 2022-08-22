package com.mdshamimislam.languagetranslate.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mdshamimislam.languagetranslate.R;
import com.mdshamimislam.languagetranslate.activity.Image_To_Text;
import com.mdshamimislam.languagetranslate.activity.VoiceToText;
import com.mdshamimislam.languagetranslate.model_class.model_class_of_Country_Image;

import java.util.ArrayList;
import java.util.List;

public class searchSpinnerValueAdapter  extends RecyclerView.Adapter<searchSpinnerValueAdapter.myAdapter> implements Filterable {
    Context c;
    List<model_class_of_Country_Image> list;
    List<model_class_of_Country_Image> listfull;
    int size;
    String refValue;
    String TAG="searchSpinnerValueAdapter";
    public searchSpinnerValueAdapter(ArrayList<model_class_of_Country_Image> data, Context context,String ref) {
        this.c = context;
        this.list = data;
        listfull= new ArrayList<>(list);
        this.refValue=ref;
    }

    @NonNull
    @Override
    public searchSpinnerValueAdapter.myAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_country_image,parent,false);
        return new myAdapter(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull searchSpinnerValueAdapter.myAdapter holder, @SuppressLint("RecyclerView") int position)
    {
        model_class_of_Country_Image temp=listfull.get(position);
        model_class_of_Country_Image model=list.get(position);
        holder.countryImage.setImageResource(model.getCountryImage());
        holder.countryName.setText(model.getCountryName());
        Log.d(TAG,"CountryName="+position);
        Log.d(TAG,"CountryNameP="+model.getCountryImage());

        holder.countryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"valueImage"+model.getCountryImage());


                 if (refValue.isEmpty())
                {
                  Log.d(TAG,"value"+holder.getAdapterPosition());
                }
                else
                {
                    if (refValue.equals("voiceSpinnerFrom"))
                    {
                        Intent intent = new Intent(c, VoiceToText.class);

                        SharedPreferences sharedpreferences = c.getSharedPreferences("SpinnerValue", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt("image", model.getCountryImage());
                        editor.putString("nameFrom",model.getCountryName());
                        editor.putInt("positionFrom",position);
                        editor.commit();
                        c.startActivity(intent);
                        ((Activity)c).finish();
                    }
                    else if (refValue.equals("voiceSpinnerTo"))
                    {
                        Intent intent1 = new Intent(c, VoiceToText.class);
                        SharedPreferences sharedpreferences = c.getSharedPreferences("SpinnerValue", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt("imageTo", model.getCountryImage());
                        editor.putString("nameTo",model.getCountryName());
                        editor.putInt("positionTo",position);
                        editor.commit();
                        c.startActivity(intent1);
                        ((Activity)c).finish();
                    }

                    else if (refValue.equals("imageSpinnerFrom"))
                    {
                        Intent intent1 = new Intent(c, Image_To_Text.class);
                        SharedPreferences sharedpreferences = c.getSharedPreferences("SpinnerValue", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt("imageToimageFrom", model.getCountryImage());
                        editor.putString("imagenameFrom",model.getCountryName());
                        editor.putInt("imagepositionFrom",position);
                        editor.commit();
                        c.startActivity(intent1);
                        ((Activity)c).finish();
                    }
                else if (refValue.equals("imageSpinnerTo"))
                {
                    Intent intent1 = new Intent(c, Image_To_Text.class);
                    SharedPreferences sharedpreferences = c.getSharedPreferences("SpinnerValue", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putInt("imageToimageTo", model.getCountryImage());
                    editor.putString("imagenameTo",model.getCountryName());
                    editor.putInt("imagepositionTo",position);
                    editor.commit();
                    c.startActivity(intent1);
                    ((Activity)c).finish();
                }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }
    private Filter searchFilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<model_class_of_Country_Image> filteredList= new ArrayList<>();
            if (charSequence==null || charSequence.length()==0)
            {
                filteredList.addAll(listfull);
            }
            else
            {
                String filterPattern=charSequence.toString().toLowerCase().trim();

                for (model_class_of_Country_Image item:listfull)
                {
                    if (item.getCountryName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
          list.clear();
            list.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class myAdapter extends RecyclerView.ViewHolder {
        ImageView countryImage;
        TextView countryName;

        public myAdapter(@NonNull View itemView) {
            super(itemView);
            countryImage=itemView.findViewById(R.id.countryImage);
            countryName=itemView.findViewById(R.id.countryName);

        }
    }

}
