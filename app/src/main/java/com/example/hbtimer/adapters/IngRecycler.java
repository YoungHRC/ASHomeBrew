package com.example.hbtimer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hbtimer.R;
import com.example.hbtimer.persistence.Alarms;
import com.example.hbtimer.persistence.DataRepository;

import java.util.List;


//TODO: highlight bar when hovering
public class IngRecycler extends RecyclerView.Adapter<IngRecycler.SubItemViewHolder>{

    private List<String> subingList;
 //   private Alarms alarm;

    protected IngRecycler(List<String> subItemList) {
        this.subingList = subItemList; //this.alarm = item;
    }
    private int position;

    @NonNull @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.therecipe_alarm_ingrbar, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    public class SubItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ingredient;
        ImageButton delete;

        SubItemViewHolder(View itemView) {
            super(itemView);
//            if(dataRepo == null)
//                dataRepo = new DataRepository(itemView.getContext());
            ingredient = itemView.findViewById(R.id.ingredient);
//            delete = itemView.findViewById(R.id.deleteIng);
//            delete.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
//            positionN = getAdapterPosition();
//            subingList.remove(positionN);
//            alarm.setIngList(subingList);
//            dataRepo.updateAlarms(alarm);
//            notifyItemChanged(positionN);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder subItemViewHolder, int i) {
        position = i;
        String subItem = subingList.get(i);
        subItemViewHolder.ingredient.setText(subItem);
    }

    @Override public int getItemCount() { return subingList.size(); }
}
