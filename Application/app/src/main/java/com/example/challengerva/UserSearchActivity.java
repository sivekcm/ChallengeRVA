package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class UserSearchActivity extends AppCompatActivity {

    RecyclerView userRV;
    DBHelper db = new DBHelper(this);
    Cursor cursor;
    UserAdapter adapter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");

        userRV = findViewById(R.id.userSearchRecyclerView);

        cursor = db.getUserData(user.getUsername());

        userRV.setLayoutManager(new LinearLayoutManager(UserSearchActivity.this));
        showResults(cursor);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_search_menu,menu);

        MenuItem item = menu.findItem(R.id.userSearchBar);
        SearchView search = (SearchView)item.getActionView();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                s = "%" + s + "%";
                cursor = db.getUserWildCard("username",s,user.getUsername());
                showResults(cursor);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }


    public void showResults(Cursor cursor)
    {
        adapter = new UserAdapter(UserSearchActivity.this,cursor);
        userRV.swapAdapter(adapter,false);
        toPublicProfileActivity(adapter,cursor);
    }

    public void toPublicProfileActivity(UserAdapter adapter, final Cursor cursor)
    {
        adapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                cursor.moveToPosition(position);
                User otherUser = new User(cursor);
                Intent intent = new Intent(UserSearchActivity.this,PublicProfileActivity.class);
                intent.putExtra("other user",otherUser);
                intent.putExtra("User Object", user.getUsername());

                startActivity(intent);
            }
        });
    }
}
