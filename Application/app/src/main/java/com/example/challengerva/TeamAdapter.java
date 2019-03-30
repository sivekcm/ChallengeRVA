package com.example.challengerva;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder>
{
    private Context context;
    private Cursor cursor;
    private OnItemClickListener listener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(TeamAdapter.OnItemClickListener listen){
        this.listener = listen;
    }

    public TeamAdapter(Context contex, Cursor cursor)
    {
        this.context = contex;
        this.cursor = cursor;
    }


    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView teamNameTxtView;
        public EditText maxPeopleRegisteredEditTxt;
        public EditText numPeopleRegisteredEditTxt;

        public TeamViewHolder(@NonNull View itemView, final OnItemClickListener listen) {
            super(itemView);

            numPeopleRegisteredEditTxt = itemView.findViewById(R.id.numPeopleRegisteredEditTxt);
            maxPeopleRegisteredEditTxt = itemView.findViewById(R.id.maxPeopleRegisteredEditTxt);
            teamNameTxtView = itemView.findViewById(R.id.teamNameTxtView);
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
    public TeamAdapter.TeamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        if (this.getItemCount() != 0)
        {
            View view = inflater.inflate(R.layout.team_item,viewGroup,false);
            return new TeamViewHolder(view,listener);
        }
        else
        {
            View view = inflater.inflate(R.layout.empty_search,viewGroup,false);
            return new TeamViewHolder(view,listener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder viewHolder, int i) {
        if (!cursor.moveToPosition(i)) {
            return;
        }
        String name = cursor.getString(0);
        //set the number of people enrolled
        //set the max number of people in that challenge

        viewHolder.teamNameTxtView.setText(name);
    }

    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}

