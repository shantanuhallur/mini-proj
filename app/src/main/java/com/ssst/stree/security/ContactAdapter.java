package com.ssst.stree.security;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.contact_item,null);
        TextView name = view.findViewById(R.id.contact_name);
        TextView number = view.findViewById(R.id.contact_number);
        ImageView image = view.findViewById(R.id.contact_image);
        image.setImageResource(R.drawable.ic_profile);
        name.setText(contacts.get(i).getName());
        number.setText(contacts.get(i).getNumber());
        return view;
    }
}
