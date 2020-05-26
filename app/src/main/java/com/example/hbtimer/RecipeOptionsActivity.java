package com.example.hbtimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.hbtimer.adapters.ListedRecycler;
import com.example.hbtimer.persistence.DataRepository;
import com.example.hbtimer.persistence.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeOptionsActivity extends AppCompatActivity implements ListedRecycler.OnRecListener, View.OnClickListener{
    private static final String TAG = "RecipeOptionsActivity";

    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ListedRecycler mListedRecycler;
    private DataRepository mRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_option_list);
        mRecyclerView = findViewById(R.id.recyclerView);
        findViewById(R.id.fab).setOnClickListener(this);

        mRepo = new DataRepository(this);
        retreiveRecipes();
        initRecyclerView();
    }

    private void retreiveRecipes(){
        //retrieves recipe list via repo connected to db
        //and sets observed list (if not null) to mrecipes and watches for changes...
        //TODO: research more about observer/triggers/listeners
        mRepo.retrieveRecipesTask().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if(mRecipes.size()>0){
                    mRecipes.clear();
                }
                if(recipes!=null){
                    mRecipes.addAll(recipes);
                }
                mListedRecycler.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
     //   BarSpacing itemDecorator = new BarSpacing(8);
    //    mRecyclerView.addItemDecoration(itemDecorator);
        mListedRecycler = new ListedRecycler(mRecipes, this);
        mRecyclerView.setAdapter(mListedRecycler);

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_bottomtothetop);
        mRecyclerView.setLayoutAnimation(animation);
    }

    @Override
    public void onRecClick(int position) {
        Intent intent = new Intent(this, TheRecPgActivity.class);
        intent.putExtra("selected_note", mRecipes.get(position));
        //This is possible because of Parcelability (serialization in android form).
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, HeaderFormActivity.class);
        startActivity(intent);
    }
}
