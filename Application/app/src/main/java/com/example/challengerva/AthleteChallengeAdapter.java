package com.example.challengerva;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.temporal.Temporal;

public class AthleteChallengeAdapter extends RecyclerView.Adapter<AthleteChallengeAdapter.AthleteChallengeViewHolder> {

    private Context context;
    private Cursor cursor;

    public AthleteChallengeAdapter(Context cont, Cursor curs)
    {
        this.context = cont;
        this.cursor = curs;
    }

    public class AthleteChallengeViewHolder extends  RecyclerView.ViewHolder
    {
        TextView challengeNameTextView;
        TextView startDateTextView;
        TextView endDateTextView;

        public AthleteChallengeViewHolder(@NonNull View itemView) {
            super(itemView);

            challengeNameTextView = itemView.findViewById(R.id.athleteChallengeNameTextView);
            startDateTextView = itemView.findViewById(R.id.athleteChallengeStartDateTextView);
            endDateTextView = itemView.findViewById(R.id.athleteChallengeEndDateTextView);
        }
    }

    @NonNull
    @Override
    public AthleteChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.athlete_challenge_item,viewGroup,false);
        return new AthleteChallengeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AthleteChallengeViewHolder viewHolder, int i) {
        if (!cursor.moveToPosition(i))
        {
            return;
        }

        String name = cursor.getString(1);
        String startDate = cursor.getString(2);
        String endDate = cursor.getString(3);

        startDate = "Start Date: " + startDate;
        endDate = "End Date: " + endDate;

        viewHolder.challengeNameTextView.setText(name);
        viewHolder.startDateTextView.setText(startDate);
        viewHolder.endDateTextView.setText(endDate);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
