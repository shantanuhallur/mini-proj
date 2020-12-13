package com.ssst.stree.security;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insertContact(Contact contact);

    @Query("SELECT * from Contact")
    List<Contact> getContacts();


    @Query("DELETE FROM Contact")
    void nukeTable();

}
