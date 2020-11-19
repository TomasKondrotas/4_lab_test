package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class AddNoteActivity extends AppCompatActivity {

    EditText edNote;
    EditText edNoteC;
    String parseVal;
    Set<String> test = new HashSet<String>();
   // Set<String> cars = new HashSet<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

/*
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(Constants.BASE_NOTE_KEY, cars);
        editor.apply();*/
        this.edNoteC = findViewById(R.id.edNoteC);
        this.edNote = findViewById(R.id.edNote);
    }

    public void onBtnSaveAndCloseClick(View view) {

        if (edNoteC.getText().toString().isEmpty() || edNote.getText().toString().isEmpty())
        {
            Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT).show();
        }else
        {

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPref.edit();
            test = sharedPref.getStringSet(Constants.BASE_NOTE_KEY, null);
            parseVal = edNote.getText().toString() + "-" + edNoteC.getText().toString();
            test.add(parseVal);
            editor.putStringSet(Constants.BASE_NOTE_KEY, test);
            //Toast.makeText(this, test.toString(), Toast.LENGTH_SHORT).show();
            editor.apply();
            finish();
        }
    }
}