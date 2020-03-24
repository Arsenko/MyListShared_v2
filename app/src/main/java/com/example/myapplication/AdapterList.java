package com.example.myapplication;

import android.content.Context;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterList {
    final String HEADING = "heading";
    final String BODY = "body";
    public List<Map<String, String>> adapterList;

    public AdapterList(String ListText) {
        adapterList = prepareContent(ListText);
    }

    @NonNull
    public SimpleAdapter createAdapter(Context context) {
        return new SimpleAdapter(context, adapterList, R.layout.list_adapter_item, new String[]{HEADING, BODY}, new int[]{R.id.heading, R.id.body});
    }

    @NonNull
    private List<Map<String, String>> prepareContent(String ListText) {
        List<Map<String, String>> temp = new ArrayList<>();
        String[] values = ListText.split("\n\n");
        for (int i = 0; i < values.length; i++) {
            Map<String, String> mapItem = new HashMap<>();
            mapItem.put(HEADING, String.valueOf(values[i].length()));
            mapItem.put(BODY, values[i]);
            temp.add(mapItem);
        }
        return temp;
    }
}
