package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        ListView lv=findViewById(R.id.myListView);
        lv.setAdapter(createAdapter());
    }

    @NonNull
    private BaseAdapter createAdapter() {
        return new SimpleAdapter(this,prepareContent(),R.layout.list_adapter_item,new String[]{"heading","body"},new int[]{R.id.heading,R.id.body});
    }

    @NonNull
    private List<Map<String, String>> prepareContent() {
        List<Map<String, String>> temp= new ArrayList<>();
        String[] values = getString(R.string.large_text).split("\n\n");
        for(int i=0;i<values.length;i++){
            Map mapItem=new HashMap<String,String >();
            mapItem.put("heading",String.valueOf(values[i].length()));
            mapItem.put("body",values[i]);
            temp.add(mapItem);
        }
        return temp;
    }
}