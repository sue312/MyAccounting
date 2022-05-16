package com.android.myaccounting.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.myaccounting.R;
import com.android.myaccounting.databases.DataProcessing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;


public class Fragment2 extends Fragment {

    private DataProcessing dp;
    public ArrayList<Map<String,String>> listItems;

    public static Fragment2 newInstance(Context context){
        Fragment2 fragment2 = new Fragment2();
        return fragment2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listItems = dp.dataTolist();
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listItems,R.layout.bill2_item, new String[]{"expenditure","income","time","reason"},new int[]{R.id.item_expenditure,R.id.item_income,R.id.item_time,R.id.item_reason});
        ListView list = view.findViewById(R.id.rank_list);
        list.setAdapter(simpleAdapter);
    }

    //Fragment和Activity相关联时调用。可以通过该方法获取Activity引用，还可以通过getArguments()获取参数。
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dp = new DataProcessing(context);
    }

}
