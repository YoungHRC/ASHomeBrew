package com.example.hbtimer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hbtimer.adapters.AlarmRecycler;
import com.example.hbtimer.persistence.DataRepository;
import com.example.hbtimer.persistence.Alarms;
import com.example.hbtimer.persistence.Recipe;

import java.util.ArrayList;
import java.util.List;


//TODO: set alarm clicker (?)
public class TheRecPgActivity extends AppCompatActivity implements View.OnClickListener {//}, AlarmRecycler.OnDeleteListener{
    private static final String TAG = "TheRecPgActivity";

    private DataRepository DataRepo;
    private TextView name, style, aIbvU;
    private ImageView color;
    private Button edit, start, stop, add;
    private Recipe note;
    private int totalAlarms;
    private AlarmRecycler itemAdapter;
    private ArrayList<Alarms> mAlarms = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_recipe);
        note = getIntent().getParcelableExtra("selected_note");
        setView();
    }

    protected void setView() {
//        color = findViewById(R.id.BeerColor);
        name = findViewById(R.id.theName);
        style = findViewById(R.id.theStyle);
        aIbvU = findViewById(R.id.the3midB);
        edit = findViewById(R.id.buttonEdit);
        start = findViewById(R.id.buttonStart);
        stop = findViewById(R.id.buttonStop);
        add = findViewById(R.id.buttonAdd);

//        color.setImageResource(note.getColor());
        name.setText(note.getTitle());
        style.setText(note.getStyle());
        aIbvU.setText("ABV: " + note.getAbv() + " and IBU: " + note.getIbu());
        edit.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        add.setOnClickListener(this);

        RecyclerView rvItem = findViewById(R.id.alarmListParent);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DataRepo = new DataRepository(this);

        retrieveAlarms();
        itemAdapter = new AlarmRecycler(mAlarms);
        rvItem.setAdapter(itemAdapter);
        rvItem.setLayoutManager(layoutManager);

        Toolbar parent = (Toolbar) findViewById(R.id.recProfileToolbar);
        parent.setPadding(0,0,0,0);
        parent.setContentInsetsAbsolute(0,0);

    }

    private void retrieveAlarms() {
        DataRepo.getRecAlarms(note.getTheid()).observe(this, new Observer<List<Alarms>>() {
            @Override
            public void onChanged(List<Alarms> alarms) {
                if (mAlarms.size() > 0) {
                    mAlarms.clear();
                }
                if (alarms != null) {
                    mAlarms.addAll(alarms);
                }
                totalAlarms = mAlarms.size();
                itemAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonEdit: {
                Intent intent = new Intent(this, HeaderFormActivity.class);
                intent.putExtra("selected_note", note);
                startActivity(intent);
                break;
            }
            case R.id.buttonStart: {
                Intent intent = new Intent(this, BrewActivity.class);
                intent.putExtra("selected_note", note); //TODO: try passing alarmed
                startActivity(intent);
                break;
            }
            case R.id.buttonStop: {
                new AlertDialog.Builder(this)
                    .setTitle("Deletion Request").setMessage("Are you sure you want to delete this recipe?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            DataRepo.deleteRecipes(note);
                            finish();                                }})
                    .setNegativeButton(android.R.string.no, null).show();
                break;
            }
            case R.id.buttonAdd: {
                DataRepo.insertAlarms(new Alarms(note.getTheid(), ++totalAlarms));
                itemAdapter.notifyItemInserted(totalAlarms);
                retrieveAlarms();
                break;
                //https://stackoverflow.com/questions/31367599/how-to-update-recyclerview-adapter-data
            }
        }
    }

    @Override
    //TODO: if back button
    public void onBackPressed() {
        Intent intent = new Intent(this, RecipeOptionsActivity.class);
        startActivity(intent);
    }

}