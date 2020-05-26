package com.example.hbtimer.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecDAO {
//Methods that are required to interact with db (Database Access Object).

    @Query("SELECT * FROM recipes")
    LiveData<List<Recipe>> getRecipes();

//    @Query("SELECT * FROM notes WHERE id = :id")
//    List<Note> getNoteWithCustomQuery(int id);
//
//    @Query("SELECT * FROM notes WHERE title LIKE :title")
//    List<Note> getNoteWithCustomQuery(String title);

    @Insert()
    long[] insertRecipes(Recipe[] recipes);

    @Delete()
    int deleteRecipes(Recipe[] recipes);

    @Update()
    int updateRecipes(Recipe[] recipes);
}
