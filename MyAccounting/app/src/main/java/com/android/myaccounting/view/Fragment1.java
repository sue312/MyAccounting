package com.android.myaccounting.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.myaccounting.R;
import com.android.myaccounting.databases.Data;
import com.android.myaccounting.databases.DataProcessing;

import java.io.Serializable;


public class Fragment1 extends Fragment {

    public TextView tv_balance;
    public EditText et_expenditure,et_income,et_reason;
    public Button btn_confirm;
    private DataProcessing dp;

    public static Fragment1 newInstance(Context context){
        Fragment1 fragment1 = new Fragment1();
        return fragment1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_balance = view.findViewById(R.id.tv_balance);
        et_expenditure = view.findViewById(R.id.et_expenditure);
        et_income = view.findViewById(R.id.et_income);
        et_reason = view.findViewById(R.id.et_reason);

        setBalance();

        btn_confirm = view.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
                setBalance();
            }
        });

    }

    //Fragment和Activity相关联时调用。可以通过该方法获取Activity引用，还可以通过getArguments()获取参数。
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dp = new DataProcessing(context);
    }

    public void addData() {
        String expenditure = et_expenditure.getText().toString();
        String income = et_income.getText().toString();
        String reason = et_reason.getText().toString();

        if (expenditure.equals("") || expenditure == null) {
            Data.setExpenditure(0);
        } else {
            Data.setExpenditure(Integer.parseInt(expenditure));
        }
        if (income.equals("") || income == null) {
            Data.setIncome(0);
        }else {
            Data.setIncome(Integer.parseInt(income));
        }
        Data.setTime(System.currentTimeMillis());
        Data.setReason(reason);

        dp.dataInsert();
    }

    public void setBalance() {
        dp.dataQuery();
        tv_balance.setText(getContext().getString(R.string.tv_balance)+ Data.getBalance());
        et_expenditure.setText("");
        et_income.setText("");
        et_reason.setText("");
    }

}
