package com.example.hbtimer.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.hbtimer.crud.*;

import java.util.List;

public class DataRepository {
//Repo called in activity in order to communicate with db/DAO
//sets what to do with separate Async methods to trigger @override doinbackground for value executed
// TODO: is async the best way?

    private TheDatabase mDatabase;
    private RecDAO mRDao;
    private AlarmDAO mADao;

    public DataRepository(Context context) {
        mDatabase = mDatabase.getInstance(context);  //single ref to db
        mRDao = mDatabase.getRecDAO();
        mADao = mDatabase.getAlarmDAO();
    }

    public void insertRecipes(Recipe recipe){ new InsertAsyncRec(mRDao).execute(recipe); }
    public void updateRecipes(Recipe recipe){
        new UpdateAsyncRec(mRDao).execute(recipe);
    }
    public void deleteRecipes(Recipe recipe){
        new DeleteAsyncRec(mRDao).execute(recipe);
    }

    public void insertAlarms(Alarms alarm){
        new InsertAsyncAlarm(mADao).execute(alarm);
    }
    public void updateAlarms(Alarms alarm){
        new UpdateAsyncAlarm(mADao).execute(alarm);
    }
    public void deleteAlarms(Alarms alarm){
        new DeleteAsyncAlarm(mADao).execute(alarm);
    }

    public LiveData<List<Recipe>> retrieveRecipesTask() {
        return mRDao.getRecipes();
    }
    public LiveData<List<Alarms>> retrieveAlarmsTask() {
        return mADao.getAlarms();
    }
    public LiveData<List<Alarms>> getRecAlarms(int recID){return mADao.getAllRecAlarms(recID);}
}
