package com.ebe.maverick;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

                ShowConfigDialog();

                return true;
            case R.id.Export_file:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    void ShowConfigDialog(){
        DialogFragment newFrag = new DialogFragment();
        newFrag.show(getFragmentManager(), "dialog");
    }

    public static class PreferenceDialogFragment extends DialogFragment
    {
        private final String LOG_TAG = getClass().getSimpleName();

        public static PreferenceDialogFragment newInstance(int title)
        {
            PreferenceDialogFragment frag = new PreferenceDialogFragment();

            Bundle args = new Bundle();

            args.putInt("title", title);

            frag.setArguments(args);

            return frag;
        }

        public void PositiveClick(MainActivity activity, View config)
        {
            try
            {


                EditText passwordText = (EditText)config.findViewById(R.id.PasswordText);
                EditText urlText = (EditText)config.findViewById(R.id.URLText);
                Spinner recruiterList = (Spinner)config.findViewById(R.id.RecruitersSpinner);

                userName.setValue(userNameText.getText().toString());
                password.setValue(passwordText.getText().toString());
                uriDomain.setValue(urlText.getText().toString());
                source.setValue(sourceText.getText().toString());


            }
            catch (SQLException e)
            {
                Log.e(LOG_TAG, "Something bad happened with the database...", e);
            }
        }

        public void NegativeClick()
        {

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            int title = getArguments().getInt("title");

            final View configDialog = getActivity().getLayoutInflater().inflate(R.layout.config, null);

            Dialog perferenceDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(title)
                    .setPositiveButton(R.string.ConfigAlertDialogOK,
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int whichButton)
                                {
                                    PositiveClick((MainActivity) getActivity(),configDialog);
                                }
                            }
                    )
                    .setNegativeButton(R.string.ConfigAlertDialogCancel,
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int whichButton)
                                {

                                }
                            }
                    )
                    .setView(configDialog)
                    .create();


            return perferenceDialog;
        }
    }


    
}
