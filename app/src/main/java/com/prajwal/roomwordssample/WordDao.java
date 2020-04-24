package com.prajwal.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    public void insert(Word word);

    @Query("DELETE FROM word_table")
    public void deleteAll();

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();   //The words returned belongs to Word class with type List and wrapped with                                             LiveData return type.

}
