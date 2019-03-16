package com.example.challengerva;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.view.Menu;

import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;


public class ChallengeSearchActivity extends AppCompatActivity {

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_search);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search Challenge...");

        //call intent method for searching
        handleIntent(getIntent());


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // Get search query
        handleIntent(getIntent());
        // Call the method to display challenges
        displayChallenges();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    DBHelper db = new DBHelper(this);
    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            //process cursor and display results
        }
    }
    public void displayChallenges(){
        ListView challengeList = (ListView) findViewById(R.id.challengeListView);
        //create an array list
        ArrayList<String> challengeArray = new ArrayList<>();
        Cursor challengeData = db.getChallengeData(query);
        //If there is nothing in the database
        if (challengeData.getCount() == 0){
            Toast.makeText(ChallengeSearchActivity.this, "No Challenges", Toast.LENGTH_LONG).show();
        }
        //populating the array list with the challenge names
        else {
            while(challengeData.moveToNext()){
                challengeArray.add(challengeData.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,challengeArray);
                challengeList.setAdapter(listAdapter);
                
            }
        }
    }
}
