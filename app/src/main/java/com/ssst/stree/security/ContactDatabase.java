package com.ssst.stree.security;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Contact.class} , version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

}
