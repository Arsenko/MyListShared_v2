package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    AdapterList adapterList;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        sp=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        if(!sp.contains("listText")) {
            ed.putString("listText", getString(R.string.large_text));
            ed.commit();
        }

        setContent().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapterList.adapterList.remove(parent.getItemAtPosition(position));
                adapter.notifyDataSetChanged();
            }
        });


        final SwipeRefreshLayout refresh=findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
                setContent();
                adapter.notifyDataSetChanged();
            }
        });
    }

    public ListView setContent(){
        adapterList=new AdapterList(sp.getString("listText",""));
        adapter=adapterList.createAdapter(this);
        ListView listView=findViewById(R.id.myListView);
        listView.setAdapter(adapter);
        return listView;
    }
}