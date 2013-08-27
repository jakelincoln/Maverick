package com.ebe.maverick;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ConfigFragment extends Activity {

    EditText adminPword;
    EditText URL;
    Spinner Source;
    Button Sync;
    Button ClearDB;
    Button adminLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        AdminLogin();
        adminLoginBtn = (Button)findViewById(R.id.AdminLogin);
        adminPword = (EditText)findViewById(R.id.PasswordText);
        URL = (EditText)findViewById(R.id.URLText);
        Source = (Spinner)findViewById(R.id.RecruitersSpinner);
        Sync = (Button)findViewById(R.id.SyncButton);
        ClearDB = (Button)findViewById(R.id.Clear_DB);

        setContentView(R.layout.activity_config);

        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AdminLogin();
            }

        });


    }


    void closeView(){
        finish();
    }

    void AdminLogin(){
        //If the user has entered the correct password then the form becomes editable
        if(adminPword.getText().toString() == "@strings/derPassword"){
            URL.setClickable(true);
            URL.setFocusable(true);
            Source.setClickable(true);
            Source.setFocusable(true);
            Sync.setClickable(true);
            ClearDB.setClickable(true);
        }
        //If the password is incorrect or on program start up the form is not editable
        else {
            URL.setClickable(false);
            URL.setFocusable(false);
            Source.setClickable(false);
            Source.setFocusable(false);
            Sync.setClickable(false);
            ClearDB.setClickable(false);
        }
        adminPword.setText("");
    }

    void clearDB(){

    }

    void syncDB(){

    }
    
}
