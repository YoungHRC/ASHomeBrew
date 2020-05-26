package com.example.hbtimer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hbtimer.persistence.DataRepository;
import com.example.hbtimer.persistence.Recipe;
import com.example.hbtimer.utils.SoftInputAssist;
import com.google.android.material.textfield.TextInputLayout;

//https://www.youtube.com/watch?v=CADXN-LAu98

public class HeaderFormActivity extends AppCompatActivity implements View.OnClickListener {//}, AdapterView.OnItemSelectedListener {
    private DataRepository mRepo;
    private SoftInputAssist softInputAssist;
 //   private RelativeLayout rlayout;
    private Animation animation;
//    private Spinner spinner;
    private String name, styled, perc, hoppy;

    private EditText beerName, style, abv, ibu;

    private boolean mIsNew, isValid;
    private Recipe activeRecipe;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_header_2);
        softInputAssist = new SoftInputAssist(this);

      //  rlayout = findViewById(R.id.rlayout);
        saveButton = findViewById(R.id.saveButton);
        beerName = findViewById(R.id.beerName);
        style = findViewById(R.id.style);
        abv = findViewById(R.id.ABV_header);
        ibu = findViewById(R.id.IBU_header);

      //  animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
     //   rlayout.setAnimation(animation);

        mRepo = new DataRepository(this);
        saveButton.setOnClickListener(this);

        if (getIntent().hasExtra("selected_note")) {
            activeRecipe = getIntent().getParcelableExtra("selected_note");
            mIsNew = false;
            beerName.setText(activeRecipe.getTitle());
            style.setText(activeRecipe.getStyle());
            abv.setText("" + activeRecipe.getAbv());
            ibu.setText("" + activeRecipe.getIbu());
        } else {
            activeRecipe = new Recipe();
            mIsNew = true;
        }

        //TODO: recheck out https://stackoverflow.com/questions/16411056/how-to-adjust-layout-when-soft-keyboard-appears
        //TODO: button custom = https://www.youtube.com/watch?v=nlPtfncjOWA 6 25
    }

    //https://www.youtube.com/watch?v=veOZTvAdzJ8
    private boolean confirmInput(){
        isValid=true;
        name = beerName.getText().toString();
        if(name.isEmpty()){
            isValid = false;
            beerName.setError("This is a required field.");
        } else{
            beerName.setError(null);
        }

        styled = style.getText().toString();
        if(styled.isEmpty()){
            isValid = false;
            style.setError("This is a required field.");
        } else{
            style.setError(null);
        }

        perc = abv.getText().toString();
        if(perc.isEmpty()){
            isValid=false;
            abv.setError("This is a required field.");
        } else{
            abv.setError(null);
        }

        hoppy = ibu.getText().toString();
        if(hoppy.isEmpty()){
            isValid=false;
            ibu.setError("This is a required field.");
        } else{
            ibu.setError(null);
        }
        return isValid;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveButton: {
                if(confirmInput()) {
                    activeRecipe.setTitle(name);
                    activeRecipe.setStyle(styled);
                    activeRecipe.setABV(Double.parseDouble(perc));
                    activeRecipe.setIBU(Integer.parseInt(hoppy));
                    if (!mIsNew)
                        mRepo.updateRecipes(activeRecipe);
                    else {
                        mRepo.insertRecipes(activeRecipe);
                    }

                    Intent intent = new Intent(this, TheRecPgActivity.class);
                    intent.putExtra("selected_note", activeRecipe); //TODO: try passing alarmed
                    startActivity(intent);
                }
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, RecipeOptionsActivity.class);
        startActivity(intent);
    }

    //THE FOLLOWING ARE FOR THE KEYBOARD/SCREEN ALIGNMENT...
    @Override
    protected void onResume(){
        softInputAssist.onResume();
        super.onResume();
    }
    @Override
    protected void onPause(){
        softInputAssist.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy(){
        softInputAssist.onDestroy();
        super.onDestroy();
    }
}