package com.example.challengerva;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.time.temporal.Temporal;

public class AthleteChallengeAdapter extends RecyclerView.Adapter<AthleteChallengeAdapter.AthleteChallengeViewHolder> {

    private Context context;
    private Cursor cursor;
    private OnItemClickListener listener;
    private User user;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AthleteChallengeAdapter.OnItemClickListener listen)
    {
        this.listener = listen;
    }

    public AthleteChallengeAdapter(Context cont, Cursor curs)
    {
        this.context = cont;
        this.cursor = curs;
    }

    public AthleteChallengeAdapter(Context cont, Cursor curs, User user)
    {
        this.context = cont;
        this.cursor = curs;
        this.user = user;
    }

    public class AthleteChallengeViewHolder extends  RecyclerView.ViewHolder
    {
        TextView challengeNameTextView;
        TextView startDateTextView;
        TextView endDateTextView;
        Button logBtn;

        public AthleteChallengeViewHolder(@NonNull View itemView, final OnItemClickListener listen) {
            super(itemView);

            challengeNameTextView = itemView.findViewById(R.id.athleteChallengeNameTextView);
            startDateTextView = itemView.findViewById(R.id.athleteChallengeStartDateTextView);
            endDateTextView = itemView.findViewById(R.id.athleteChallengeEndDateTextView);
            logBtn = itemView.findViewById(R.id.athleteChallengeLogBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listen != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listen.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public AthleteChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.athlete_challenge_item,viewGroup,false);
        return new AthleteChallengeViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final AthleteChallengeViewHolder viewHolder, int i) {
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
        if (this.user.isLoggedUser())
        {
            final DBHelper db = new DBHelper(context);
            final Cursor cursor = db.getChallengeData("name",viewHolder.challengeNameTextView.getText().toString());
            cursor.moveToNext();
            boolean hasLogged = db.hasLoggedToday(user.getUsername(),cursor.getInt(0));

            if (!hasLogged) {
                viewHolder.logBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, LogChallengeActivity.class);
                        Challenge challenge = new Challenge(cursor);
                        intent.putExtra("activity", "AthleteViewChallengeActivity");
                        intent.putExtra("User Object", user);
                        intent.putExtra("challenge", challenge);


                        ((Activity)context).startActivityForResult(intent,0);


                    }
                });

            }
            else
            {
                viewHolder.logBtn.setEnabled(false);
                viewHolder.logBtn.setClickable(false);
                viewHolder.logBtn.setBackgroundColor(context.getResources().getColor(R.color.Orange));
            }
        }
        else
        {
            viewHolder.logBtn.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
