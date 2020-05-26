package com.example.hbtimer.persistence;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipe implements Parcelable {
    @PrimaryKey(autoGenerate = true) private int theid;
    @ColumnInfo(name = "title") private String title;
    @ColumnInfo(name = "style") private String style;
    @ColumnInfo(name = "abv") private double abv;
    @ColumnInfo(name = "ibu") private int ibu;

    public Recipe(String title, String style, double abv, int ibu) {
        this.title = title;
        this.style = style;
        this.abv = abv;
        this.ibu = ibu;
    }

    protected Recipe(Parcel in) {
        theid = in.readInt();
        title = in.readString();
        style = in.readString();
        abv = in.readDouble();
        ibu = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(theid);
        parcel.writeString(title);
        parcel.writeString(style);
        parcel.writeDouble(abv);
        parcel.writeInt(ibu);
    }
    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }
        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getTheid() { return theid; }
    public String getTitle() { return title; }
    public String getStyle() { return style; }
    public int getIbu() {
//        String value = Double.toString(ibu);
//        if(value.length() <= 4){
//            return value;
//        }
//        else return value.substring(0,4);
        return ibu;
    }
//    public int getColor() {
//        switch (color) {
//            case 0:
//                return R.drawable.cream;
//            case 1:
//                return R.drawable.pale;
//            case 2:
//                return R.drawable.amber;
//            case 3:
//                return R.drawable.deepamber;
//            case 4:
//                return R.drawable.brwn;
//            case 5:
//                return R.drawable.dkbrwn;
//        }
//        return R.drawable.white;
//    }
//    public int getColorNum(){
//        return color;
//    }

    public double getAbv() {
        return abv;
    }
    //TODO: if null, unk  and  put max on field

    public void setTheid(int id) { this.theid = id; }
    //TODO: does the recDAO_Imp use of this disrupt the alarms?
    public void setTitle(String title) { this.title = title; }
    public void setStyle(String style) { this.style = style; }
    public void setABV(double abv) { this.abv = abv; }
    public void setIBU(int ibu) { this.ibu = ibu; }

    @Override
    public String toString() {
        return "Recipe{" + "id=" + theid + ", title=" + title  + '}';
    }

    @Override public int describeContents() { return 0; }
    @Ignore public Recipe() {   }

}
