package com.ssst.stree.security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ssst.stree.R;

public class ContactForm extends AppCompatActivity {

    private Contact contact;
    private ContactDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
        setDB();

        final EditText name = findViewById(R.id.name);
        final EditText number = findViewById(R.id.phone_number);

        contact = (Contact) getIntent().getSerializableExtra("contact");
        name.setText(contact.getName());
        number.setText(contact.getNumber());

        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.contactDao().deleteContact(contact);
                ContactForm.this.finish();
            }
        });

        Button update = findViewById(R.id.save);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.setName(name.getText().toString().trim());
                contact.setNumber(number.getText().toString().trim());
                db.contactDao().updateContact(contact);
                ContactForm.this.finish();
            }
        });
    }

    private void setDB(){
        db = Room.databaseBuilder(ContactForm.this,ContactDatabase.class,"Contacts").allowMainThreadQueries().build();
    }
}