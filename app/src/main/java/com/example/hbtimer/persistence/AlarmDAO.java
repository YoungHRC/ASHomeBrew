package com.example.hbtimer.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlarmDAO {

    @Query("SELECT * FROM alarms")
    LiveData<List<Alarms>> getAlarms();

//    @Query("SELECT * FROM notes WHERE id = :id")
//    List<Note> getNoteWithCustomQuery(int id);

//    TODO: LIKE vs =

    @Query("SELECT * FROM alarms WHERE recipeId LIKE :recID")  //ORDER BY alarmNum ASC
    LiveData<List<Alarms>> getAllRecAlarms(int recID);

    @Insert()
    long[] insertAlarms(Alarms[] alarms);

    @Delete()
    int deleteAlarms(Alarms[] alarms);

    @Update()
    int updateAlarms(Alarms[] alarms);
}
