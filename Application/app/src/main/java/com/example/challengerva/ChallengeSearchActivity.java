
package com.example.challengerva;


import android.app.SearchManager;
import android.content.Intent;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class ChallengeSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Initialize variabales
    RecyclerView challengeRV;
    Spinner spinner;
    String[] filterArr;
    Cursor challengeData;
    ChallengeAdapter adapter;
    DBHelper db = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_search);

        //Sets recycle view upon opening activity
        challengeRV = findViewById(R.id.challengeRV);
        challengeData = db.getChallengeData();
        showResults(challengeData);
    }

    /***************************************
     * onCreateOptionsMenu
     * @param menu
     * @return
     *
     * Creates the option menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Sets the format and contents of menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        //Instantiates spinner and searchview for the menu
        MenuItem item = menu.findItem(R.id.search);
        MenuItem spinnerItem = menu.findItem(R.id.filter);
        SearchView searchView = (SearchView) item.getActionView();
        spinner = (Spinner) spinnerItem.getActionView();

        //sets up the spinner
        ArrayAdapter<CharSequence> arrAdapter = ArrayAdapter.createFromResource(this, R.array.filters, android.R.layout.simple_spinner_item);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrAdapter);

        filterArr = getResources().getStringArray(R.array.filters);

        //when spinner option is selected
        spinner.setOnItemSelectedListener(this);

        //Sets up the search view to update recycler view upon press enter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                query = "%" + query + "%";

                //shows challenges where the name contains what was entered in the search view
                challengeData = db.getChallengeWildCard("name",query);
                showResults(challengeData);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    /*************************************************************
     * filterResults method
     * @param spin
     * @param arr
     * @param pos
     *
     * filters the results according to what is selected
     */
    public void filterResults(Spinner spin, String[] arr, int pos)
    {
        //makes sure array is less than size 9
        assert arr.length <= 8;

        //checks what item is selected by the spinner
        if (spin.getSelectedItem().toString().equals(arr[pos]))
        {
            //if filter is difficulty low - high
            if (pos == 1)
            {
                String[] formatArr = arr[pos].split("\\s+");
                challengeData = db.getChallengeDataAsc(formatArr[0]);
                showResults(challengeData);
            }

            //if filter is difficulty high - low
            else if (pos == 2)
            {
                String[] formatArr = arr[pos].split("\\s+");
                challengeData = db.getChallengeDataDesc(formatArr[0]);
                showResults(challengeData);
            }

            //if filter is start date ascending
            else if (pos == 3)
            {
                challengeData = db.getChallengeDataDateAsc();
                showResults(challengeData);
            }

            //if filter is start date descending
            else if (pos == 4)
            {
                challengeData = db.getChallengeDataDateDesc();
                showResults(challengeData);
            }

            //if filter is type (lifting, cardio, or health)
            else if (pos == 5 || pos == 6 || pos == 7)
            {
                String selected = spin.getSelectedItem().toString();
                String type = selected.substring(selected.indexOf('(') + 1,selected.indexOf(")"));
                challengeData = db.getChallengeData("type",type);
                showResults(challengeData);
            }
        }
    }

    /*************************************************
     * showResults method
     * @param cursor the cursor containing the challenges to be displayed
     *
     * Displays the challenges contained in cursor on the recylcerview
     */
    public void showResults(Cursor cursor)
    {
        challengeRV.setLayoutManager(new LinearLayoutManager(ChallengeSearchActivity.this));
        adapter = new ChallengeAdapter(ChallengeSearchActivity.this, cursor);
        challengeRV.swapAdapter(adapter,false);
    }


    /*****************************************************************************************
     * onItemSelected method
     * @param parent
     * @param view
     * @param position
     * @param id
     *
     * Defines what the app does when an item is selected in the spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //if filter is difficulty low - high
        filterResults(spinner,filterArr,1);

        //if filter is difficulty high - low
        filterResults(spinner,filterArr,2);

        //if filter is start date ascending
        filterResults(spinner,filterArr,3);

        //if filter is start date descending
        filterResults(spinner,filterArr,4);

        //if filter is lifting
        filterResults(spinner,filterArr,5);

        //if filter is cardio
        filterResults(spinner,filterArr,6);

        //if filter is health
        filterResults(spinner,filterArr,7);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
