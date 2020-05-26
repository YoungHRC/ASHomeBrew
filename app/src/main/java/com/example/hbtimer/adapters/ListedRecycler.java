package com.example.hbtimer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hbtimer.R;
import com.example.hbtimer.persistence.Recipe;

import java.util.ArrayList;

public class ListedRecycler extends RecyclerView.Adapter<ListedRecycler.ViewHolder>{
    private static final String TAG = "ListedRecycler";
    private OnRecListener mOnRecListener;
    private ArrayList<Recipe> mRecipes;

    //saving data list/listener to memory
    public ListedRecycler(ArrayList<Recipe> rec, OnRecListener onRecListener) {
        this.mRecipes = rec;
        this.mOnRecListener = onRecListener;
    }

    //TODO: inputsearch AND logo animation (and main screen)
    //TODO: allow users input pic in circle format or default to color icon
    //TODO: sort by?

    @NonNull  @Override  //adds a recipe bar for each array item - one bar to the parent page view at a time
    //prompted when RecipeRecyler is created, which needs the list and listener to be created..
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_option_bar, parent,false);
        return new ViewHolder(view, mOnRecListener);
    }

    //sets descriptive variable (and listener) to relevant lines in bar's view
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title, style, abvNibu;
        OnRecListener onRecListener;

        public ViewHolder(@NonNull View itemView, OnRecListener onRecListener) {
            super(itemView);
            title = itemView.findViewById(R.id.recipeName);
            style = itemView.findViewById(R.id.beer_style);
    //        abv = itemView.findViewById(R.id.ABV_Value);
    //        ibu = itemView.findViewById(R.id.IBU_Value);
            abvNibu = itemView.findViewById(R.id.abv_ibu);
            this.onRecListener = onRecListener;
            itemView.setOnClickListener(this);
        }//Recycler View calls on Bind View Holder

        @Override  //The onClick for the above listener is set to trigger the activity's overrode onRecClick method in order to pass bar's position before going to the next screen.
        public void onClick(View view) {
            onRecListener.onRecClick(getAdapterPosition());
        }
    }

    @Override  //sets data to newest bar's view
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        try {
            holder.title.setText(mRecipes.get(i).getTitle());
            holder.style.setText(mRecipes.get(i).getStyle());
        //    holder.abv.setText(Double.toString(mRecipes.get(i).getAbv()));
        //    holder.ibu.setText("" + mRecipes.get(i).getIbu());
            holder.abvNibu.setText(mRecipes.get(i).getAbv() + "% | " + mRecipes.get(i).getIbu() + " IBUs");
        } catch(NullPointerException e){
            Log.d(TAG, "onBindViewHolder: NullPter Exc" + e.getMessage());
        }
    }

    //We're using this method to allow this adapter to pass value to the activity (by overriding this method).
    // faux listener - just a method that is called when real listener is triggered...
    // So the activity could see it as a listener because it runs on Click...
    public interface OnRecListener{ void onRecClick(int position); }
    @Override public int getItemCount() { return mRecipes.size(); }
}
