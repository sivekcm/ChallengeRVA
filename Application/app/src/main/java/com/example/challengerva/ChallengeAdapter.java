package com.example.challengerva;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder>
{

    private Context context;
    private Cursor cursor;

    public ChallengeAdapter(Context cont, Cursor curs)
    {
        this.context = cont;
        this.cursor = curs;
    }
    public class ChallengeViewHolder extends RecyclerView.ViewHolder
    {
        TextView challengeNameTextView;
        TextView challengeCoachTextView;
        TextView challengeDiffTextView;
        TextView challengeCatTextView;
        TextView challengeDurTextView;
        TextView challengeDescTextView;

        public ChallengeViewHolder(@NonNull View itemView) {
            super(itemView);

            challengeNameTextView = itemView.findViewById(R.id.searchChallengeNameTextView);
            challengeCoachTextView = itemView.findViewById(R.id.searchChallengeCoachTextView);
            challengeDiffTextView = itemView.findViewById(R.id.searchChallengeDiffTextView);
            challengeCatTextView = itemView.findViewById(R.id.searchChallengeCatTextView);
            challengeDurTextView = itemView.findViewById(R.id.searchChallengeDurTextView);
            challengeDescTextView = itemView.findViewById(R.id.searchChallengeDescTextView);
        }
    }

    @NonNull
    @Override
    public ChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.challenge,viewGroup,false);
        return new ChallengeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeViewHolder challengeViewHolder, int i) {
        if (!cursor.moveToPosition(i))
        {
            return;
        }

        String name = cursor.getString(1);
        String coach = cursor.getString(2);
        String startDate = cursor.getString(3);

        int difficulty = cursor.getInt(6);
        String category = cursor.getString(5);
        String description = cursor.getString(10);

        challengeViewHolder.challengeNameTextView.setText(name);
        challengeViewHolder.challengeCoachTextView.setText(coach);
        challengeViewHolder.challengeDurTextView.setText(startDate);
        challengeViewHolder.challengeDiffTextView.setText(String.valueOf(difficulty));
        challengeViewHolder.challengeCatTextView.setText(category);
        challengeViewHolder.challengeDescTextView.setText(description);

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
