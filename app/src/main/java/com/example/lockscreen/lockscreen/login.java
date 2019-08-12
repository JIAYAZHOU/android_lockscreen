package com.example.lockscreen.lockscreen;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt = (EditText) findViewById(R.id.passwd);

    }


    public void pwconfirm(View v){
        if(edt.getText().toString().equals("123456"))
        {
            Intent intent = new Intent(this,SetTimeActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"请输入正确的密码！",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("hello", "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("hello", "onRestart: ");
        finish();
    }
}
