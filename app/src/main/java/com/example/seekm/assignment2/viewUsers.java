package com.example.seekm.assignment2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class viewUsers extends AppCompatActivity {
    DatabaseHelper myDB;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        myDB = new DatabaseHelper(this);

        listView = (ListView)findViewById(R.id.listView);

        populateListView();

    }

    private void populateListView() {
        Cursor data = myDB.getData();
        final ArrayList<String> listData = new ArrayList<>();
        final ArrayList<String> listUsername = new ArrayList<>();
        final ArrayList<String> listEmail = new ArrayList<>();

        while (data.moveToNext()){
            listData.add(data.getString(1));
            listUsername.add(data.getString(2));
            listEmail.add(data.getString(3));
        }
        ListAdapter adapter = new ArrayAdapter<>(viewUsers.this,android.R.layout.simple_list_item_1,listData);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =  new Intent (viewUsers.this,Profile.class);
                intent.putExtra("Name", listView.getItemAtPosition(i).toString());
                intent.putExtra("Username", listUsername.get(i));
                intent.putExtra("Email", listEmail.get(i));

                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
    }
}
