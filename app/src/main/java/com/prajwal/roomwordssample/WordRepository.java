package com.prajwal.roomwordssample;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.List;

public class WordRepository {

    public WordDao mWordDao;
    public LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords(); //calls this function and fetches all the words.
    }

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    public void insert(Word word)
    {
        InsertAsyncTask insertAsyncTask = new InsertAsyncTask(mWordDao);
        insertAsyncTask.execute(word);
    }

    private class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mWordDaoS;
        public InsertAsyncTask(WordDao wordDao) {
            mWordDaoS = wordDao;

        }


        @Override
        protected Void doInBackground(Word... words) {
            mWordDaoS.insert(words[0]);
            return null;
        }
    }
}
