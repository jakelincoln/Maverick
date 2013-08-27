package com.ebe.maverick;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.action_settings:
                showConfigDialog();

            case R.id.action_Export:

        }
        return true;
    }

    void showConfigDialog()
    {
        FragmentManager fm = getFragmentManager();
        ConfigDialogFragment test = new ConfigDialogFragment();
        test.setRetainInstance(true);
        test.show(fm,"fragment_name");
    }

    public class ConfigDialogFragment extends DialogFragment
    {
        EditText adminPword;
        EditText URL;
        EditText SourceText;
        Spinner Source;
        Button Sync;
        Button ClearDB;
        Button adminLoginBtn;
        Button closebtn;
        boolean enable;
        SharedPreferences.Editor editor;
        SharedPreferences pref;

        public ConfigDialogFragment(){}

        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
        {
            View view = inflater.inflate(R.layout.activity_config,container);
            enable = false;
            adminPword = (EditText)view.findViewById(R.id.PasswordText);
            URL = (EditText)view.findViewById(R.id.URLText);
            SourceText = (EditText)view.findViewById(R.id.SourceText);
            Source = (Spinner)view.findViewById(R.id.RecruitersSpinner);
            Sync = (Button)view.findViewById(R.id.SyncButton);
            ClearDB = (Button)view.findViewById(R.id.Clear_DB);
            adminLoginBtn = (Button)view.findViewById(R.id.AdminLogin);
            closebtn = (Button)view.findViewById(R.id.Close);
            editor = getPreferences(MODE_PRIVATE).edit();
            pref = getPreferences(MODE_PRIVATE);

            URL.setText(pref.getString("URL","No Value Entered"));
            SourceText.setText(pref.getString("SourceText","No Source"));
            Source.setSelection(0);
            Source.setSelected(false);

            URL.setEnabled(false);
            Source.setEnabled(false);

            closebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(enable){
                        editor.putString("URL",URL.getText().toString());
                        if(Source.getSelectedItemPosition() != 0){
                            editor.putString("Source",Source.getSelectedItem().toString());
                        }
                        editor.commit();
                    }
                    dismiss();

                }
            });

            adminLoginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enable = AdminLogin(adminPword.getText().toString());
                    URL.setEnabled(enable);
                    Source.setClickable(enable);
                    Sync.setClickable(enable);
                    ClearDB.setClickable(enable);
                    adminPword.setText("");
                }
            });

            Sync.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Synch With Server Code Here!
                }
            });

            return view;
        }

        boolean AdminLogin(String text){
            //If the user has entered the correct password then the form becomes editable
            String password = getResources().getString(R.string.derPassword);
            if(text.equals(password))
                return true;
            //If the password is incorrect or on program start up the form is not editable
            else
                return false;
        }
    }
}
