package com.example.final_project_7082;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton buttonShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //buttonShare = findViewById(R.id.buttonShare);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        FloatingActionButton baf = findViewById(R.id.HomeButton);
            baf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(getSupportFragmentManager().getPrimaryNavigationFragment())
                            .navigate(R.id.action_global_FirstFragment);
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void shareEvent(String eventUri) {
        //Bitmap icon = mBitmap;
        Intent share = new Intent(Intent.ACTION_SEND);
        //share.putExtra(Intent.EXTRA_TEXT, Uri.parse(eventUri));
        share.putExtra(Intent.EXTRA_TEXT, "This is some text that I am sharing.");
        share.setType("text/plain");

        startActivity(Intent.createChooser(share, "Share Event"));
    }

    public void shareButton(View view) {
        //List<Photo> eventList = eventDao.getAllEvents();

        //Event event = eventList.get(eventNumber);
        //shareEvent(event.getEventUri());
        shareEvent("1");
    }
}