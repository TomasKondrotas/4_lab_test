package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner decisionSpinner;
    Set<String> test = new HashSet<String>();
    String[] test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        test = sharedPref.getStringSet(Constants.BASE_NOTE_KEY, null);

        String[] test2 = new String[test.size()];
        test.toArray(test2);

        this.decisionSpinner = findViewById(R.id.del_decision_spinner);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, test2);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        decisionSpinner.setAdapter(spinnerArrayAdapter);

    }

    public void btnDelConfirmClick(View view){

        String spSelectedOption = decisionSpinner.getSelectedItem().toString();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        test = sharedPref.getStringSet(Constants.BASE_NOTE_KEY, null);
        test.remove(spSelectedOption);


    }
}
