package com.example.hbtimer.persistence;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Entity(tableName = "alarms")  //TODO: set foreign key to not get error
//, foreignKeys = @ForeignKey(entity = Alarms.class,
//                parentColumns = "theid",
//                childColumns = "recipeId",
//                onDelete = ForeignKey.CASCADE)
public class Alarms implements Parcelable {
    @PrimaryKey(autoGenerate = true) private int alarmId;
    @ColumnInfo(name = "recipeId") private int recipeId;
    @ColumnInfo(name = "order") private int order;
    //TODO: Get Num and put it in the EditText Name
    @ColumnInfo(name = "minutes") private double minutes;
    @ColumnInfo(name = "ingredList") private String ingredList;
  //  @Embedded private Alarms nextAlarm;

    public Alarms(int recipeId, int order) {
        this.recipeId = recipeId;
        this.order = order;
        this.minutes = 1;
        this.ingredList = "tested here";
    }

    protected Alarms(Parcel in) {
        alarmId = in.readInt();
        order = in.readInt();
        minutes = in.readDouble();
        ingredList = in.readString();
        recipeId = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(alarmId);
        parcel.writeInt(order);
        parcel.writeString(ingredList);
        parcel.writeDouble(minutes);
        parcel.writeInt(recipeId);
    }
    public static final Creator<Alarms> CREATOR = new Creator<Alarms>() {
        @Override public Alarms createFromParcel(Parcel in) {
            return new Alarms(in);
        }
        @Override public Alarms[] newArray(int size) {
            return new Alarms[size];
        }
    };

    public int getOrder(){ return order; }
    public double getMinutes() { return minutes;  }
    public int getAlarmId(){return alarmId;}
    public int getRecipeId(){return recipeId;}

    public void setRecipeId(int recipeId){this.recipeId=recipeId;}
    public void setOrder(int title) { this.order = title; }
    public void setMinutes(double minutes){this.minutes=minutes;}

    //YOU ACTUALLY FUCKING NEED THIS... it turns out that the autogenerate library CODE USES THIS method!!!!!!  You're welcome. *SIGH*
    public void setAlarmId(int id){ this.alarmId = id;}

    public String getIngredList(){return ingredList;}
    public List<String> getIngList() {
        String[] test = ingredList.split("\\s*,\\s*");
        List<String> ingtList = new ArrayList<>(Arrays.asList(test));
        return ingtList;
    }
    public void setIngList(List<String> lists){
        StringBuilder csvBuilder = new StringBuilder();
        for(String list : lists){
            csvBuilder.append(list);
            csvBuilder.append(",");
        }
        String csv = csvBuilder.toString();
        csv = csv.substring(0, csv.length() - 1);
        this.ingredList = csv;
    }
    public String toString(){
        return "Alarm{ id:" + alarmId + ", #" + order  + ",  recId" + recipeId + ", " + ingredList;
    }

    public void setIngredList(String ingredList){this.ingredList = ingredList;}

    @Override public int describeContents() { return 0; }
    @Ignore public Alarms() {   }
}




