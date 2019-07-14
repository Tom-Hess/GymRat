package com.example.gymrat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.gymrat.R;
import com.google.android.gms.common.api.Status;

// Add an import statement for the client library.
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class SearchGymsActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent mainActivity = new Intent(SearchGymsActivity.this, MainActivity.class);
                    startActivity(mainActivity);
                    return true;
                case R.id.navigation_search:
                    return true;
                case R.id.navigation_add_review:
                    Intent addReview = new Intent(SearchGymsActivity.this, AddReview.class);
                    startActivity(addReview);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gyms);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        EditText commentsEditText = (EditText)findViewById(R.id.et_comments);
        navigation.setSelectedItemId(R.id.navigation_search);
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

}
