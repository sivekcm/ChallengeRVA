package com.example.challengerva;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.support.v7.widget.RecyclerView;

//
//import android.widget.TextView;
public class TeamAdapter// extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
{}
//    RecyclerView teamSelectionRecyclerView;
//    AthleteChallengeAdapter adapter;
//    DBHelper db = new DBHelper(this);
//    Cursor teamData;
//    User user;
//
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Intent intent = getIntent();
//        user = intent.getParcelableExtra("User Object");
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull TeamAdapter.ChallengeViewHolder challengeViewHolder, int i) {
//        if (!cursor.moveToPosition(i))
//        {
//            return;
//        }
//
//        String name = cursor.getString(1);
//        String coach = cursor.getString(2);
//        String startDate = cursor.getString(3);
//
//        int difficulty = cursor.getInt(6);
//        String category = cursor.getString(5);
//        String description = cursor.getString(10);
//
//        challengeViewHolder.challengeNameTextView.setText(name);
//        challengeViewHolder.challengeCoachTextView.setText(coach);
//        challengeViewHolder.challengeDurTextView.setText(startDate);
//        challengeViewHolder.challengeDiffTextView.setText(String.valueOf(difficulty));
//        challengeViewHolder.challengeCatTextView.setText(category);
//        challengeViewHolder.challengeDescTextView.setText(description);
//
//    }
//
//
//}
