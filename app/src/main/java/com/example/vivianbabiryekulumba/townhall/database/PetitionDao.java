package com.example.vivianbabiryekulumba.townhall.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PetitionDao {

    @Insert()
    void insert(Petitions petition);

    @Query("DELETE FROM petition_table")
    void deleteAll();

    @Query("SELECT * from petition_table ORDER BY petition_title ASC")
    LiveData<List<Petitions>> getAllPetitions();

}
