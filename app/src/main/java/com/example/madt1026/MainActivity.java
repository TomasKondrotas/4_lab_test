package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listNoteItems = new ArrayList<>();
    ArrayList<String> listNoteItemsC = new ArrayList<>();

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterC;

    ListView lvNotes;
    ListView lvNotesC;
    Set<String> sharePrefData;
    String[] split;
    String removeChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lvNotes = findViewById(R.id.lvNotes);
        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.listNoteItems);
        this.lvNotes.setAdapter(adapter);

        this.lvNotesC = findViewById(R.id.lvNotesC);
        this.adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.listNoteItemsC);
        this.lvNotesC.setAdapter(adapterC);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_options_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharePrefData = sharedPref.getStringSet(Constants.BASE_NOTE_KEY, null);

        this.listNoteItems.clear();
        this.listNoteItemsC.clear();
        this.adapter.notifyDataSetChanged();
        this.adapterC.notifyDataSetChanged();

        for(String i : sharePrefData)
        {
            split = i.split("-");

            removeChar = Collections.singleton(split[0]).toString();
            removeChar = removeChar.replace("[","");
            removeChar = removeChar.replace("]","");
            this.listNoteItems.add(removeChar);
            this.adapter.notifyDataSetChanged();

            removeChar = Collections.singleton(split[1]).toString();
            removeChar = removeChar.replace("[","");
            removeChar = removeChar.replace("]","");
            this.listNoteItemsC.add(removeChar);
            this.adapterC.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_note:
                Intent i = new Intent(this, AddNoteActivity.class);
                startActivity(i);
                return true;

                case R.id.del_note:
                Intent a = new Intent(this, DeleteNoteActivity.class);
                startActivity(a);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

