package com.example.dell.attendence;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StudentBean studentBean=new StudentBean(getApplicationContext());
        studentBean.checkConnection();
    }
}
