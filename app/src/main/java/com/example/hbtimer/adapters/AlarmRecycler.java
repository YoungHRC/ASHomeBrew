package com.example.hbtimer.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hbtimer.R;
import com.example.hbtimer.persistence.Alarms;
import com.example.hbtimer.persistence.DataRepository;

import java.util.List;

//TODO: need better Listeners
//TODO: condense button titles to pictures
// TODO: add screen transitions  and make cuter
// TODO: make alarms draggable to reorder and dropdown for minutes and editable/drop down by stages on title
//TODO: on click to get better performance(?) - check performance
//TODO: scroll back to top bar when all alarms are deleted outside of view
//TODO: "New Ingredient Here"
//TODO: add edit Alarm and functionality code

public class AlarmRecycler extends RecyclerView.Adapter<AlarmRecycler.ItemViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<Alarms> itemList;
    private Alarms item;
    private List<String> ing;
    private EditText addNew;
    private DataRepository dataRepo;
    private IngRecycler ingRecycler;
    private int positionN;

    public AlarmRecycler(List<Alarms> itemList){
        this.itemList = itemList;
    }

    @NonNull @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.therecipe_alarm_list, viewGroup, false);
        return new ItemViewHolder(view);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView itemTitle, time;
        private ImageButton deleteAlarm, addButton;
        private RecyclerView rvSubItem;

        ItemViewHolder(View itemView) {
            super(itemView);
            if(dataRepo == null)
                dataRepo = new DataRepository(itemView.getContext());
            itemTitle = itemView.findViewById(R.id.alarmName);
            rvSubItem = itemView.findViewById(R.id.ingrChildList);
            deleteAlarm = itemView.findViewById(R.id.deleteAlarm);

            time = itemView.findViewById(R.id.alarmTime);
            deleteAlarm.setOnClickListener(this);
        }
            @Override
            public void onClick(View view) {
                positionN = getAdapterPosition();
                if(view.getId() == R.id.deleteAlarm) {
                    Alarms toDelete = itemList.get(positionN);
                    dataRepo.deleteAlarms(toDelete);
                    itemList.remove(itemList.get(positionN));
                    notifyItemRemoved(positionN);
                }
                else {
                    Alarms toUpdate = itemList.get(positionN);
                    ing = toUpdate.getIngList();
                    ing.add(addNew.getText().toString());
                    toUpdate.setIngList(ing);
                    dataRepo.updateAlarms(toUpdate);
                    ingRecycler.notifyItemChanged(positionN);
                    addNew.getText().clear();
                }
                //TODO: yes or no
            }
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, int i) {
        item = itemList.get(i);
//        itemViewHolder.itemTitle.setText("Alarm " + item.getAlarmNum());
        itemViewHolder.time.setText(item.getMinutes() + " MINUTES");

        ing = item.getIngList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(        // Setting up the nested recycler
                itemViewHolder.rvSubItem.getContext(),
                RecyclerView.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(ing.size());         // Create layout with initial ingredient list item count
        ingRecycler = new IngRecycler(ing);          // Creates nested recycler's view adapter
        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
        itemViewHolder.rvSubItem.setAdapter(ingRecycler);
        itemViewHolder.rvSubItem.setRecycledViewPool(viewPool);
    }

    @Override  public int getItemCount() {
        return itemList.size();
    }

}
