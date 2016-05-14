package com.example.kongsin.editable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextSwitcher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ItemAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Object> lists;
    Bundle saveState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveState = new Bundle();
        lists = new ArrayList<>();
        lists.add("HELLO");
        lists.add("JAVA");
        lists.add("PHP");
        lists.add("HTML");
        lists.add("SWIFT");
        lists.add("KOTLIN");
        lists.add("JAVA SCRIPT");
        lists.add("DELPHI");
        lists.add("RUBY");
        lists.add("NODE");

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(this, lists, 0);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (adapter.viewMode == 0) {
            adapter = new ItemAdapter(this, lists, 1);
            recyclerView.setAdapter(adapter);
            item.setTitle("Cancel");
        } else {
            adapter = new ItemAdapter(this, lists, 0);
            recyclerView.setAdapter(adapter);
            item.setTitle("Edit");
        }
        return true;
    }
}
