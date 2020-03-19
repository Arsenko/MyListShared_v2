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
    private SharedPreferences sp;
    private AdapterList adapterList;
    private SimpleAdapter adapter;
    private ArrayList<Integer> delitedIndex=new ArrayList<>();
    private static final String KEY="deleted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(savedInstanceState);
    }

    public void init(Bundle savedInstanceState){
        sp=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        if(!sp.contains("listText")) {
            ed.putString("listText", getString(R.string.large_text));
            ed.commit();
        }

        setContent().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapterList.adapterList.remove(position);
                adapter.notifyDataSetChanged();
                delitedIndex.add(position);
            }
        });

        if(savedInstanceState!=null) {
            delitedIndex = savedInstanceState.getIntegerArrayList(KEY);
            deleteIndexes();
        }

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(KEY,delitedIndex);
        super.onSaveInstanceState(outState);
    }

    private void deleteIndexes(){
        for (int i=0;i<delitedIndex.size();i++) {
            adapterList.adapterList.remove(delitedIndex.get(i).intValue());
        }
        adapter.notifyDataSetChanged();
    }
}