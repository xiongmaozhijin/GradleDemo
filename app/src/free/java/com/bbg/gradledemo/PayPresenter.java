package com.bbg.gradledemo;

import android.content.Context;
import android.widget.Toast;

import com.bbg.gradledemo.IPay;

public class PayPresenter implements IPay {


    @Override
    public String dealPayFee(Context context) {
        Toast.makeText(context, "处理不收费", Toast.LENGTH_SHORT).show();
        return "处理不收费";
    }

}
