package com.example.hbtimer.persistence;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Recipe.class, Alarms.class}, version =1)
public abstract class TheDatabase extends RoomDatabase {
    //You have to include .db to open it in SqLite DB Browser... after saving the db
    //files from inside the Device File Explorer of your emulator.
    //https://www.youtube.com/watch?v=trswX8x-dnI
    public static final String DATABASE_NAME = "Recipe.db";

    private static TheDatabase instance;

    //Singleton method - to keep only one active db connection...
    static TheDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TheDatabase.class, DATABASE_NAME).build();
        }
        return instance;
    }
    public abstract AlarmDAO getAlarmDAO();
    public abstract RecDAO getRecDAO();

}
