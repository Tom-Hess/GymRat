package com.example.gymrat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.gymrat.Model.Review;
import com.example.gymrat.R;
import com.example.gymrat.sqlite.database.DatabaseHelper;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class AddReview extends AppCompatActivity {


    private Spinner squatRacksSpinner;
    private BottomNavigationView navigation;
    private Spinner benchPressesSpinner;
    private Button addReviewButton;
    private EditText commentsEditText;
    private RatingBar overallRatingBar;
    private DatabaseHelper db;

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
        db = new DatabaseHelper(this);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        commentsEditText = (EditText)findViewById(R.id.et_comments);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        addReviewButton = (Button) findViewById(R.id.btnAddReview);
        navigation.setSelectedItemId(R.id.navigation_add_review);
        overallRatingBar = findViewById(R.id.overallRatingBar);
        initializeSpinners();
        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Review newReview = new Review();
                //TODO: fix bad code
                newReview.setBenchPresses(Integer.parseInt(benchPressesSpinner.getSelectedItem().toString()));
                newReview.setSquatRacks(Integer.parseInt(squatRacksSpinner.getSelectedItem().toString()));
                newReview.setComment(commentsEditText.getText().toString());
                newReview.setOverallRating(overallRatingBar.getNumStars());
                //TODO: add more fields
                // Add review to database
                db.insertReview(newReview);
            }
        });

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyBKz2lUZl_wGyIUSwonKfCWs-p26H1UyGw");

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);

        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyBKz2lUZl_wGyIUSwonKfCWs-p26H1UyGw");
        }

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.TYPES,
                Place.Field.OPENING_HOURS, Place.Field.PHONE_NUMBER));
        autocompleteFragment.setTypeFilter(TypeFilter.ESTABLISHMENT);
        autocompleteFragment.setHint("Search gyms");
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.d("d","Coordinates: " + place.getLatLng());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });
    }

    public void initializeSpinners(){
        squatRacksSpinner = (Spinner) findViewById(R.id.squat_racks_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> numberArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.number_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        numberArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the numberArrayAdapter to the spinner
        squatRacksSpinner.setAdapter(numberArrayAdapter);

        benchPressesSpinner = (Spinner) findViewById(R.id.bench_presses_spinner);
        benchPressesSpinner.setAdapter(numberArrayAdapter);
    }

}
