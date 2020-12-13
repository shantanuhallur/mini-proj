package com.ssst.stree.security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ListView;

import com.ssst.stree.R;
import java.util.List;

public class EmergencyNumbers extends AppCompatActivity {

    private ContactDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_numbers);

        ListView emergencyNumbers = findViewById(R.id.emergency_contacts);
        setDB();

        Contact contact = new Contact("Shantanu","9665308970");
        Contact contact1 = new Contact("Shardul","7499184548");
        Contact contact2 = new Contact("Tejas","8308857721");
        Contact contact3 = new Contact("Sahil","9422429871");
        db.contactDao().nukeTable();
        db.contactDao().insertContact(contact);
        db.contactDao().insertContact(contact1);
        db.contactDao().insertContact(contact2);
        db.contactDao().insertContact(contact3);
        List<Contact> contactList = db.contactDao().getContacts();

        ContactAdapter contactAdapter = new ContactAdapter(getApplicationContext(), contactList);
        emergencyNumbers.setAdapter(contactAdapter);
    }

    private void setDB(){
        db = Room.databaseBuilder(EmergencyNumbers.this,ContactDatabase.class,"Contacts").allowMainThreadQueries().build();
    }
}