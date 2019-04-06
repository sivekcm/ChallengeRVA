package com.example.challengerva;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder> {
    private Context context;
    private Cursor cursor;
    private OnItemClickListener listener;

    public interface  OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(TeamMemberAdapter.OnItemClickListener listen)
    {
        this.listener = listen;
    }

    public TeamMemberAdapter(Context context, Cursor cursor)
    {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public TeamMemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.team,viewGroup,false);
        return new TeamMemberViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamMemberViewHolder teamMemberViewHolder, int i) {
        if (!cursor.moveToPosition(i))
        {
            return;
        }
        String name = cursor.getString(2);
        teamMemberViewHolder.userTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class TeamMemberViewHolder extends  RecyclerView.ViewHolder
    {
        TextView userTextView;
        public TeamMemberViewHolder(@NonNull View itemView, final OnItemClickListener listen) {
            super(itemView);

            userTextView = itemView.findViewById(R.id.specific_team);
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
