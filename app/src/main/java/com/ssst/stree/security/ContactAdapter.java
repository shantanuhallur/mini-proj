package com.ssst.stree.security;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssst.stree.R;
import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private final Context context;
    private final List<Contact> contacts;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contacts.get(i).getId();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.contact_item,null);
        TextView name = view.findViewById(R.id.contact_name);
        TextView number = view.findViewById(R.id.contact_number);
        ImageView image = view.findViewById(R.id.contact_image);
        image.setImageResource(R.drawable.ic_profile);
        name.setText(contacts.get(i).getName());
        number.setText(contacts.get(i).getNumber());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactForm(view,contacts.get(i));
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactForm(view,contacts.get(i));
            }
        });
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactForm(view,contacts.get(i));
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactForm(view,contacts.get(i));
            }
        });
        return view;
    }

    private void openContactForm(View view,Contact contact) {
        Intent intent = new Intent(view.getContext(),ContactForm.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("contact",contact);
        view.getContext().startActivity(intent);
    }
}
