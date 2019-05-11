package com.example.gymrat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AddReview extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent addReview = new Intent(AddReview.this, MainActivity.class);
                    startActivity(addReview);
                    return true;
                case R.id.navigation_search:
                    Intent searchGyms = new Intent(AddReview.this, SearchGymsActivity.class);
                    startActivity(searchGyms);
                    return true;
                case R.id.navigation_add_review:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_add_review);
        initializeSpinners();
    }

    public void initializeSpinners(){
        Spinner spinner = (Spinner) findViewById(R.id.squat_racks_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

}
