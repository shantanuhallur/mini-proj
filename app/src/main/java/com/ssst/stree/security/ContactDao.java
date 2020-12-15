package com.ssst.stree.security;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insertContact(Contact contact);

    @Query("SELECT * FROM Contact")
    List<Contact> getContacts();

    @Query("DELETE FROM Contact")
    void nukeTable();

    @Delete
    void deleteContact(Contact contact);

    @Update
    void updateContact(Contact contact);

}
