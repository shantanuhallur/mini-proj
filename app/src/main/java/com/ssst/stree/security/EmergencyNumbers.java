package com.ssst.stree.security;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ssst.stree.R;
import java.util.List;

public class EmergencyNumbers extends AppCompatActivity {

    private static final int PICK_CONTACT = 1;
    private ContactDatabase db;
    private ListView emergencyNumbers;
    public static List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_numbers);

        emergencyNumbers = findViewById(R.id.emergency_contacts);
        setDB();

        FloatingActionButton addButton = findViewById(R.id.addContact);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContacts();
            }
        });
    }

    private void openContacts() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent,PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_CONTACT && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            Cursor c = getContentResolver().query(uri,null,null,null,null);

            if(c.moveToFirst()) {
                String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Contact contact = new Contact(name,number);
                db.contactDao().insertContact(contact);
            }

            c.close();
        }
    }

    private void setDB(){
        db = Room.databaseBuilder(EmergencyNumbers.this,ContactDatabase.class,"Contacts").allowMainThreadQueries().build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactList = db.contactDao().getContacts();
        ContactAdapter contactAdapter = new ContactAdapter(getApplicationContext(), contactList);
        emergencyNumbers.setAdapter(contactAdapter);
    }
}