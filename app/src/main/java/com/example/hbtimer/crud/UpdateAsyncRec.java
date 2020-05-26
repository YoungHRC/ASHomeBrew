package com.example.hbtimer.crud;

import android.os.AsyncTask;

import com.example.hbtimer.persistence.RecDAO;
import com.example.hbtimer.persistence.Recipe;

public class UpdateAsyncRec extends AsyncTask<Recipe, Void, Void> {
    private RecDAO mRDao;

    public UpdateAsyncRec(RecDAO dao) {
        mRDao = dao;
    }

    @Override
    protected Void doInBackground(Recipe[] recipes) {
        mRDao.updateRecipes(recipes);
        return null;
    }
}
