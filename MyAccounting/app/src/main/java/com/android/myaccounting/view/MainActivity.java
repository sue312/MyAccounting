package com.android.myaccounting.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.myaccounting.R;
import com.android.myaccounting.databases.Data;
import com.android.myaccounting.databases.DataProcessing;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "wangxin666";
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Button btn_fr1,btn_fr2,btn_fr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        Bundle bundle = new Bundle();
        bundle.putString("et2",Data.balance+"");
        intent.putExtras(bundle);
        this.sendBroadcast(intent);
    }

    private void findView() {
        fragment1 = Fragment1.newInstance(this);
        fragment2 = Fragment2.newInstance(this);
        btn_fr1 = findViewById(R.id.btn_fr1);
        btn_fr2 = findViewById(R.id.btn_fr2);
        btn_fr3 = findViewById(R.id.btn_fr3);

        btn_fr1.setOnClickListener(this);
        btn_fr2.setOnClickListener(this);
        btn_fr3.setOnClickListener(this);
        getFragmentManager().beginTransaction().add(R.id.fr,fragment1).commitNowAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fr1:
                getFragmentManager().beginTransaction().replace(R.id.fr, fragment1).commitNowAllowingStateLoss();
                setFocus(btn_fr1);
                break;
            case R.id.btn_fr2:
                getFragmentManager().beginTransaction().replace(R.id.fr, fragment2).commitNowAllowingStateLoss();
                setFocus(btn_fr2);
                break;
            case R.id.btn_fr3:
                setFocus(btn_fr3);
                break;
            default:
                break;
        }
    }

    private void setFocus(Button b) {
        switch (b.getId()) {
            case R.id.btn_fr1:
                btn_fr1.setBackgroundResource(R.drawable.btn_focus);
                btn_fr2.setBackgroundResource(R.drawable.btn_no_focus);
                btn_fr3.setBackgroundResource(R.drawable.btn_no_focus);
                break;
            case R.id.btn_fr2:
                btn_fr1.setBackgroundResource(R.drawable.btn_no_focus);
                btn_fr2.setBackgroundResource(R.drawable.btn_focus);
                btn_fr3.setBackgroundResource(R.drawable.btn_no_focus);
                break;
            case R.id.btn_fr3:
                btn_fr1.setBackgroundResource(R.drawable.btn_no_focus);
                btn_fr2.setBackgroundResource(R.drawable.btn_no_focus);
                btn_fr3.setBackgroundResource(R.drawable.btn_focus);
                break;
            default:
                break;
        }
    };
}