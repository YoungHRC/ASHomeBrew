package com.example.hbtimer.crud;

import android.os.AsyncTask;

import com.example.hbtimer.persistence.AlarmDAO;
import com.example.hbtimer.persistence.Alarms;

public class DeleteAsyncAlarm extends AsyncTask<Alarms, Void, Void> {
    private AlarmDAO mADao;

    public DeleteAsyncAlarm(AlarmDAO dao) {
        mADao = dao;
    }

    @Override
    protected Void doInBackground(Alarms[] alarms) {
        mADao.deleteAlarms(alarms);
        return null;
    }
}
