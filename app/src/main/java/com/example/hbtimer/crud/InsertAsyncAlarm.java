package com.example.hbtimer.crud;

import android.os.AsyncTask;

import com.example.hbtimer.persistence.AlarmDAO;
import com.example.hbtimer.persistence.Alarms;

public class InsertAsyncAlarm extends AsyncTask<Alarms, Void, Void> {
    private AlarmDAO mADao;

    public InsertAsyncAlarm(AlarmDAO dao) {
        mADao = dao;
    }

    @Override
    protected Void doInBackground(Alarms[] alarms) {
        mADao.insertAlarms(alarms);
        return null;
    }
}
