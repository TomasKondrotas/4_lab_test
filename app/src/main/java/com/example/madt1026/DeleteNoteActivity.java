package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner decisionSpinner;
    Set<String> sharedPrefData = new HashSet<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPrefData = sharedPref.getStringSet(Constants.BASE_NOTE_KEY, null);

        String[] test2 = new String[sharedPrefData.size()];
        sharedPrefData.toArray(test2);

        this.decisionSpinner = findViewById(R.id.del_decision_spinner);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, test2);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        decisionSpinner.setAdapter(spinnerArrayAdapter);
    }

    public void btnDelConfirmClick(View view){

        //Jei SharedPrefrences yra tuščias bandant ištrinti jo elementa activity_delete_note lužta (nors po to atrodo viskas vistiek gerai veikia)
        String spSelectedOption = decisionSpinner.getSelectedItem().toString();

        if(spSelectedOption == null || spSelectedOption.isEmpty())
        {
            Toast.makeText(this, R.string.emptySpin, Toast.LENGTH_SHORT).show();
            finish();
        }else
        {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            sharedPrefData = sharedPref.getStringSet(Constants.BASE_NOTE_KEY, null);
            sharedPrefData.remove(spSelectedOption);
            finish();
        }
    }
}
