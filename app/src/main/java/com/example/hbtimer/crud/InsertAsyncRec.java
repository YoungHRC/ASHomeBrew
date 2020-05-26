package com.example.hbtimer.crud;

import android.os.AsyncTask;

import com.example.hbtimer.persistence.RecDAO;
import com.example.hbtimer.persistence.Recipe;

public class InsertAsyncRec extends AsyncTask<Recipe, Void, Void> {
    private RecDAO mRDao;

    public InsertAsyncRec(RecDAO dao) {
        mRDao = dao;
    }

    @Override
    protected Void doInBackground(Recipe[] recipes) {
        mRDao.insertRecipes(recipes);
        return null;
    }
}
