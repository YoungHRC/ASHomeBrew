package com.example.hbtimer.crud;

import android.os.AsyncTask;

import com.example.hbtimer.persistence.RecDAO;
import com.example.hbtimer.persistence.Recipe;

public class DeleteAsyncRec extends AsyncTask<Recipe, Void, Void> {
    private RecDAO mRDao;

    public DeleteAsyncRec(RecDAO dao) {
        mRDao = dao;
    }

    @Override
    protected Void doInBackground(Recipe[] recipes) {
        //TODO: deleteAlarms of the recipe, when status is finished (might happen in Alarms)
        mRDao.deleteRecipes(recipes);
        return null;
    }
}